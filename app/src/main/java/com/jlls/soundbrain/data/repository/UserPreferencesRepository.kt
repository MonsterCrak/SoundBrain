package com.jlls.soundbrain.data.repository

import com.jlls.soundbrain.domain.model.MusicStyle
import com.jlls.soundbrain.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository interface for user preferences.
 */
interface UserPreferencesRepository {
    fun getPreferences(): Flow<UserPreferences>
    suspend fun updatePreferences(preferences: UserPreferences)
    suspend fun incrementSongsGenerated()
}

/**
 * Implementation of UserPreferencesRepository.
 */
class UserPreferencesRepositoryImpl : UserPreferencesRepository {

    private var preferences = UserPreferences()

    override fun getPreferences(): Flow<UserPreferences> = flow {
        emit(preferences)
    }

    override suspend fun updatePreferences(newPrefs: UserPreferences) {
        preferences = newPrefs
    }

    override suspend fun incrementSongsGenerated() {
        preferences = preferences.copy(songsGeneratedCount = preferences.songsGeneratedCount + 1)
    }
}
