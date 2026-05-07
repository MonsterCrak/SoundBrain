package com.jlls.soundbrain.domain.usecase

import com.jlls.soundbrain.data.repository.SongRepository
import com.jlls.soundbrain.domain.model.MusicStyle
import com.jlls.soundbrain.domain.model.Song
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving all songs.
 */
class GetSongsUseCase(private val repository: SongRepository) {
    operator fun invoke(): Flow<List<Song>> = repository.getSongs()
}

/**
 * Use case for deleting a song by ID.
 */
class DeleteSongUseCase(private val repository: SongRepository) {
    suspend operator fun invoke(songId: String) = repository.deleteSong(songId)
}

/**
 * Use case for generating a new AI song.
 */
class GenerateSongUseCase(private val repository: SongRepository) {
    suspend operator fun invoke(prompt: String, style: MusicStyle): Song =
        repository.generateSong(prompt, style)
}
