package com.RCS.my_app.models

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)