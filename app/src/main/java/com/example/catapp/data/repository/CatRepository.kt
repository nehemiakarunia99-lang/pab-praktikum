package com.example.catapp.data.repository

import com.example.catapp.data.remote.ApiService
import com.example.catapp.data.remote.CatBreedResponse
import com.example.catapp.data.remote.CatImageResponse
import javax.inject.Inject
import javax.inject.Singleton

class CatRepository(
    private val apiService: ApiService
) {
    suspend fun fetchRandomCatImage(): List<CatImageResponse> {
        return apiService.getRandomCatImage()
    }

    suspend fun fetchBreeds(): List<CatBreedResponse> {
        return apiService.getBreeds()
    }

    suspend fun fetchImagesByBreed(breedId: String): List<CatImageResponse> {
        return apiService.getImagesByBreed(breedId)
    }
}
