// APIClient.kt
package com.example.pyi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private val apiService: ApiService

    init {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getIdeas(): Idea2? {
        return try {
            apiService.getIdeas()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
