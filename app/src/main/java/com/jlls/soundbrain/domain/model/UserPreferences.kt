package com.jlls.soundbrain.domain.model

/**
 * User preferences for the SoundBrain app.
 *
 * @property songsGeneratedCount Total songs generated
 * @property apiKey Stored API key (should be encrypted in production)
 * @property isDarkTheme Dark mode preference
 * @property defaultStyle Default music style for generation
 */
data class UserPreferences(
    val songsGeneratedCount: Int = 0,
    val apiKey: String = "",
    val isDarkTheme: Boolean = false,
    val defaultStyle: MusicStyle = MusicStyle.LOFI
)
