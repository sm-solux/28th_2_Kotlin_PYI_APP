package com.example.pyi

import com.google.gson.annotations.SerializedName

// RegistrationRequest.kt

data class RegistrationRequest(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)

// RegistrationResponse.kt

data class RegistrationResponse(
    @SerializedName("message") val message: String
)

// LoginResponse.kt

data class LoginResponse(
    @SerializedName("message") val message: String
)
