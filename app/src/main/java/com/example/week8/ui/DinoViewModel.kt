package com.example.week8.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week8.ui.network.DinoApi
import com.example.week8.ui.network.DinoPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinoUiState {
    data class Success(val photos: List<DinoPhoto>) : DinoUiState
    object Error : DinoUiState
    object Loading : DinoUiState
}

class DinoViewModel : ViewModel() {
    var dinoUiState: DinoUiState by mutableStateOf(DinoUiState.Loading)
        private set

    init {
        getDinoPhotos()
    }

    fun getDinoPhotos() {
        viewModelScope.launch {
            dinoUiState = try {
                DinoUiState.Success(DinoApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinoUiState.Error
            }
        }
    }
}