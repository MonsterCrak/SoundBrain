package com.jlls.soundbrain.data.repository

import com.jlls.soundbrain.data.mock.MockMusicDataSource
import com.jlls.soundbrain.domain.model.MusicStyle
import com.jlls.soundbrain.domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository interface for song operations.
 */
interface SongRepository {
    fun getSongs(): Flow<List<Song>>
    fun getSongById(songId: String): Flow<Song?>
    suspend fun deleteSong(songId: String)
    suspend fun generateSong(prompt: String, style: MusicStyle): Song
}

/**
 * Implementation of SongRepository with mock data.
 */
class SongRepositoryImpl : SongRepository {

    private val songs = mutableListOf<Song>().apply { addAll(MockMusicDataSource.songs) }

    override fun getSongs(): Flow<List<Song>> = flow {
        emit(songs.sortedByDescending { it.createdAt })
    }

    override fun getSongById(songId: String): Flow<Song?> = flow {
        emit(songs.find { it.id == songId })
    }

    override suspend fun deleteSong(songId: String) {
        songs.removeAll { it.id == songId }
    }

    override suspend fun generateSong(prompt: String, style: MusicStyle): Song {
        val newSong = Song(
            id = "SONG-${System.currentTimeMillis()}",
            title = prompt.take(40).ifEmpty { "Untitled" },
            coverUrl = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=400",
            promptStyle = style,
            createdAt = System.currentTimeMillis(),
            duration = (180..300).random()
        )
        songs.add(0, newSong)
        return newSong
    }
}
