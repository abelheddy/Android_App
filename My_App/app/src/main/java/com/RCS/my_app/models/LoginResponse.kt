package com.RCS.my_app.models

import com.RCS.my_app.api.ApiUserData
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String, // Cambiado de access_token
    @SerializedName("user") val user: ApiUserData
)

data class BasicResponse(
    @SerializedName("msg") val message: String,
    @SerializedName("error") val error: String? = null
)
/*
data class UserData(
    val id: Int,
    val name: String,
    val email: String
)*/