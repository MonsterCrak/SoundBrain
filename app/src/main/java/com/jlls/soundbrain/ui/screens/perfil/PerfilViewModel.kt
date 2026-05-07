package com.jlls.soundbrain.ui.screens.perfil

import androidx.lifecycle.ViewModel
import com.jlls.soundbrain.data.repository.UserPreferencesRepository
import com.jlls.soundbrain.data.repository.UserPreferencesRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the Perfil (Profile) screen.
 */
class PerfilViewModel(
    private val preferencesRepository: UserPreferencesRepository = UserPreferencesRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    fun toggleTheme() {
        _uiState.value = _uiState.value.copy(
            isDarkTheme = !_uiState.value.isDarkTheme
        )
    }

    fun saveApiKey(apiKey: String) {
        _uiState.value = _uiState.value.copy(apiKey = apiKey)
    }
}

/**
 * UI State for Perfil screen.
 */
data class PerfilUiState(
    val songsGenerated: Int = 12,
    val apiKey: String = "",
    val isDarkTheme: Boolean = false,
    val storageUsedMB: Int = 45
)
