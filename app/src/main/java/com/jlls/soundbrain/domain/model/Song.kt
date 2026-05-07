package com.jlls.soundbrain.domain.model

/**
 * Song domain model representing a generated AI song.
 *
 * @property id Unique identifier
 * @property title Song title
 * @property artistName Generated or user-provided artist name
 * @property coverUrl URL to album cover image
 * @property promptStyle The AI style used (Lo-fi, Tech-House, etc.)
 * @property createdAt Timestamp of generation
 * @property duration Duration in seconds (optional)
 * @property audioUrl URL to generated audio (optional, for future use)
 */
data class Song(
    val id: String,
    val title: String,
    val artistName: String = "SoundBrain AI",
    val coverUrl: String,
    val promptStyle: MusicStyle,
    val createdAt: Long = System.currentTimeMillis(),
    val duration: Int? = null,
    val audioUrl: String? = null
)
