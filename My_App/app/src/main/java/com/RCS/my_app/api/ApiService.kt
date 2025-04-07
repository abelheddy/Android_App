package com.RCS.my_app.api

import com.RCS.my_app.models.BasicResponse
import com.RCS.my_app.models.ForgotPasswordRequest
import com.RCS.my_app.models.LoginRequest
import com.RCS.my_app.models.LoginResponse
import com.RCS.my_app.models.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    // Ajusta los endpoints para que coincidan con tu Node.js
    @POST("api/auth/login") // Cambiado de "api/login"
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Agrega otros endpoints que necesites
    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<LoginResponse>

    @POST("api/auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<BasicResponse>
}
