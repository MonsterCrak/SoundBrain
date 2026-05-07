package com.jlls.soundbrain.ui.screens.generador

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlls.soundbrain.data.repository.SongRepositoryImpl
import com.jlls.soundbrain.domain.model.MusicStyle
import com.jlls.soundbrain.domain.model.Song
import com.jlls.soundbrain.domain.usecase.GenerateSongUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Generador (AI Creator) screen.
 */
class GeneradorViewModel(
    private val generateSongUseCase: GenerateSongUseCase = GenerateSongUseCase(
        SongRepositoryImpl()
    )
) : ViewModel() {

    private val _uiState = MutableStateFlow(GeneradorUiState())
    val uiState: StateFlow<GeneradorUiState> = _uiState.asStateFlow()

    fun updatePrompt(prompt: String) {
        _uiState.value = _uiState.value.copy(prompt = prompt)
    }

    fun selectStyle(style: MusicStyle) {
        _uiState.value = _uiState.value.copy(selectedStyle = style)
    }

    fun generateSong() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isGenerating = true)

            // Simulate generation delay
            delay(2500)

            val newSong = generateSongUseCase(
                prompt = _uiState.value.prompt,
                style = _uiState.value.selectedStyle
            )

            _uiState.value = _uiState.value.copy(
                isGenerating = false,
                generatedSong = newSong
            )
        }
    }

    fun clearGeneratedSong() {
        _uiState.value = _uiState.value.copy(generatedSong = null)
    }
}

/**
 * UI State for Generador screen.
 */
data class GeneradorUiState(
    val prompt: String = "",
    val selectedStyle: MusicStyle = MusicStyle.LOFI,
    val isGenerating: Boolean = false,
    val generatedSong: Song? = null
)
