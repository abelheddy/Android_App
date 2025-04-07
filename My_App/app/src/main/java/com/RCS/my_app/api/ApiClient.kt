package com.RCS.my_app.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "http://tudominio.epizy.com/"  // Asegúrate que es HTTP/HTTPS correcto

    // Configuración del logger
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Client con timeouts y logger
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)  // Agregar el logger
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // Instancia de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicio API
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    // Interceptor para autenticación
    fun createAuthenticatedClient(token: String): ApiService {
        val authClient = okHttpClient.newBuilder()
            .addInterceptor(getAuthInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(authClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun getAuthInterceptor(token: String) = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("x-auth-token", token)
            .build()
        chain.proceed(newRequest)
    }
}