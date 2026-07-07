package com.example.catapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("images/search")
    suspend fun getRandomCatImage(): List<CatImageResponse>

    @GET("breeds")
    suspend fun getBreeds(): List<CatBreedResponse>

    @GET("images/search")
    suspend fun getImagesByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 10
    ): List<CatImageResponse>
}
