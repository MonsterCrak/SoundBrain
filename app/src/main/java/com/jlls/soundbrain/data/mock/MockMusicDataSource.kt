package com.jlls.soundbrain.data.mock

import com.jlls.soundbrain.domain.model.MusicStyle
import com.jlls.soundbrain.domain.model.Song

/**
 * Mock data source for music AI songs.
 * Contains sample songs with Unsplash cover images.
 */
object MockMusicDataSource {

    val songs: List<Song> = listOf(
        Song(
            id = "SONG-001",
            title = "Midnight Dreams",
            coverUrl = "https://images.unsplash.com/photo-1514525253161-7a46d19cd819?w=400",
            promptStyle = MusicStyle.LOFI,
            createdAt = System.currentTimeMillis() - 86400000,
            duration = 180
        ),
        Song(
            id = "SONG-002",
            title = "Electric Pulse",
            coverUrl = "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=400",
            promptStyle = MusicStyle.TECH_HOUSE,
            createdAt = System.currentTimeMillis() - 172800000,
            duration = 240
        ),
        Song(
            id = "SONG-003",
            title = "Epic Journey",
            coverUrl = "https://images.unsplash.com/photo-1478737270239-2f02b77fc618?w=400",
            promptStyle = MusicStyle.CINEMATIC,
            createdAt = System.currentTimeMillis() - 259200000,
            duration = 300
        ),
        Song(
            id = "SONG-004",
            title = "Smooth Groove",
            coverUrl = "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=400",
            promptStyle = MusicStyle.JAZZ,
            createdAt = System.currentTimeMillis() - 345600000,
            duration = 210
        ),
        Song(
            id = "SONG-005",
            title = "Rainy Vibes",
            coverUrl = "https://images.unsplash.com/photo-1459749411175-04bf5292ceea?w=400",
            promptStyle = MusicStyle.LOFI,
            createdAt = System.currentTimeMillis() - 432000000,
            duration = 195
        )
    )
}
