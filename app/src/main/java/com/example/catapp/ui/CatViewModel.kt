package com.example.catapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.remote.CatBreedResponse
import com.example.catapp.data.remote.CatImageResponse
import com.example.catapp.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CatUiState(
    val catImageUrl: String? = null,
    val breedList: List<CatBreedResponse> = emptyList(),
    val selectedBreed: CatBreedResponse? = null,
    val breedImages: List<CatImageResponse> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class CatViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatUiState())
    val uiState: StateFlow<CatUiState> = _uiState.asStateFlow()

    fun selectBreed(breed: CatBreedResponse) {
        _uiState.update { it.copy(selectedBreed = breed, breedImages = emptyList()) }
        loadBreedImages(breed.id)
    }

    private fun loadBreedImages(breedId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val images = repository.fetchImagesByBreed(breedId)
                _uiState.update { it.copy(breedImages = images, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun loadRandomCatImage() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val response = repository.fetchRandomCatImage()
                val url = response.firstOrNull()?.url
                _uiState.update { it.copy(catImageUrl = url, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun loadBreeds() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val breeds = repository.fetchBreeds()
                _uiState.update { it.copy(breedList = breeds, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}
