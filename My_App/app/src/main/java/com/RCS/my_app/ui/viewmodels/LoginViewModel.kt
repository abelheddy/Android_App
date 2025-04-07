package com.RCS.my_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.RCS.my_app.api.ApiClient
import com.RCS.my_app.api.ApiUserData
import com.RCS.my_app.models.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException


class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        data class Success(
            val token: String,
            val user: ApiUserData  // Ahora está correctamente definido
        ) : LoginState()
        data class Error(val message: String) : LoginState()
    }

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.login(
                    LoginRequest(
                        email = email,
                        password = password
                    )
                )

                when {
                    response.isSuccessful -> {
                        response.body()?.let { loginResponse ->
                            // Guardar el token en preferencias
                            saveAuthToken(loginResponse.token)

                            _loginState.value = LoginState.Success(
                                token = loginResponse.token,
                                user = loginResponse.user
                            )
                        }
                    }
                    else -> {
                        val errorBody = response.errorBody()?.string()
                        val errorMsg = try {
                            // Intenta parsear el error del backend Node.js
                            val errorJson = JSONObject(errorBody)
                            errorJson.getString("msg") ?: "Error desconocido"
                        } catch (e: Exception) {
                            "Credenciales incorrectas"
                        }
                        _loginState.value = LoginState.Error(errorMsg)
                    }
                }
            } catch (e: IOException) {
                _loginState.value = LoginState.Error("Error de conexión: ${e.message}")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error inesperado: ${e.localizedMessage}")
            }
        }
    }
    private fun saveAuthToken(token: String) {
        // Implementa el guardado seguro del token (SharedPreferences o DataStore)
    }
}