package com.example.imageappktor.data.remote

import com.example.imageappktor.data.model.Image
import retrofit2.http.GET

interface ImageApi {

    @GET("/randomImage")
    suspend fun getRandomImage(): Image

    companion object {
        const val BASE_URL = "http://192.168.0.2:8100"
    }
}