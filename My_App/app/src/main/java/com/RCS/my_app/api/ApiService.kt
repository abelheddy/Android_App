package com.RCS.my_app.api

import com.RCS.my_app.models.LoginRequest
import com.RCS.my_app.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")  // Asegúrate que esta ruta coincida con tu backend Laravel
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}