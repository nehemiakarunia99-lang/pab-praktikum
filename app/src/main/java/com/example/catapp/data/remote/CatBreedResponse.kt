package com.example.catapp.data.remote

data class CatBreedResponse(
    val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?
)
