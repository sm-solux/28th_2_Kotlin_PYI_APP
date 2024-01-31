package com.example.pyi

import retrofit2.http.GET

interface ApiService {
    //나중에 수정하기!!
    @GET("vieworganize/1")
    suspend fun getIdeas(): Idea2 // Idea 클래스에 맞게 수정
}
