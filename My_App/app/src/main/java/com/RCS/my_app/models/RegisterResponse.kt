package com.RCS.my_app.models

import com.RCS.my_app.api.ApiUserData
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("access_token") val token: String,
    @SerializedName("token_type") val tokenType: String,
    val user: ApiUserData
)
