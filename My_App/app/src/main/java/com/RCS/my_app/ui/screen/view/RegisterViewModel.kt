package com.RCS.my_app.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.RCS.my_app.api.ApiClient
import com.RCS.my_app.api.ApiUserData
import com.RCS.my_app.models.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class RegisterViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    sealed class RegisterState {
        object Idle : RegisterState()
        object Loading : RegisterState()
        data class Success(
            val token: String,
            val user: ApiUserData
        ) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.register(
                    RegisterRequest(
                        name = name,
                        email = email,
                        password = password,
                        password_confirmation = confirmPassword
                    )
                )

                if (response.isSuccessful) {
                    response.body()?.let { registerResponse ->
                        _registerState.value = RegisterState.Success(
                            token = registerResponse.token,
                            user = registerResponse.user
                        )
                    } ?: run {
                        _registerState.value = RegisterState.Error("Respuesta vacía del servidor")
                    }
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Error desconocido"
                    _registerState.value = RegisterState.Error(errorMsg)
                }
            } catch (e: IOException) {
                _registerState.value = RegisterState.Error("Error de conexión")
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Error: ${e.localizedMessage}")
            }
        }
    }
}