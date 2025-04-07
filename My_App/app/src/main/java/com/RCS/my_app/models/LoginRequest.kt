package com.RCS.my_app.models

data class LoginRequest(
    val email: String,
    val password: String
)
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String // Opcional, depende de tu backend
)