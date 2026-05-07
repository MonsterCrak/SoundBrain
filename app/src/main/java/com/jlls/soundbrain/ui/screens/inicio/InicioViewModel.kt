package com.jlls.soundbrain.ui.screens.inicio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlls.soundbrain.data.repository.SongRepository
import com.jlls.soundbrain.data.repository.SongRepositoryImpl
import com.jlls.soundbrain.domain.model.Song
import com.jlls.soundbrain.domain.usecase.DeleteSongUseCase
import com.jlls.soundbrain.domain.usecase.GetSongsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Inicio (Library) screen.
 */
class InicioViewModel(
    private val getSongsUseCase: GetSongsUseCase = GetSongsUseCase(SongRepositoryImpl()),
    private val deleteSongUseCase: DeleteSongUseCase = DeleteSongUseCase(SongRepositoryImpl())
) : ViewModel() {

    private val _uiState = MutableStateFlow(InicioUiState())
    val uiState: StateFlow<InicioUiState> = _uiState.asStateFlow()

    init {
        loadSongs()
    }

    private fun loadSongs() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getSongsUseCase().collect { songs ->
                _uiState.value = _uiState.value.copy(
                    songs = songs,
                    recentSongs = songs.take(5),
                    isLoading = false
                )
            }
        }
    }

    fun selectSong(song: Song) {
        _uiState.value = _uiState.value.copy(selectedSong = song)
    }

    fun playSong(song: Song) {
        _uiState.value = _uiState.value.copy(currentlyPlaying = song)
    }

    fun shareSong(song: Song) {
        // Share implementation - would use Android share intent
    }

    fun deleteSong(song: Song) {
        viewModelScope.launch {
            deleteSongUseCase(song.id)
            loadSongs()
        }
    }
}

/**
 * UI State for Inicio screen.
 */
data class InicioUiState(
    val songs: List<Song> = emptyList(),
    val recentSongs: List<Song> = emptyList(),
    val selectedSong: Song? = null,
    val currentlyPlaying: Song? = null,
    val isLoading: Boolean = true
)
