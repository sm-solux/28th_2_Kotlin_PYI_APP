package com.example.pyi

import retrofit2.http.GET

interface ApiService {
    @GET("your/ideas/endpoint")
    suspend fun getIdeas(): List<Idea> // Idea 클래스에 맞게 수정
}
