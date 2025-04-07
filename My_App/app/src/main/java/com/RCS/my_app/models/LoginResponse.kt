package com.RCS.my_app.models

import com.RCS.my_app.api.ApiUserData
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val token: String,
    @SerializedName("token_type") val tokenType: String,
    val user: ApiUserData // Usa el nuevo nombre
)
/*
data class UserData(
    val id: Int,
    val name: String,
    val email: String
)*/