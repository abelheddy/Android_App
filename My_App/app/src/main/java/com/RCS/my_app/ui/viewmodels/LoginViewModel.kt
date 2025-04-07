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
                            _loginState.value = LoginState.Success(
                                token = loginResponse.token,
                                user = loginResponse.user
                            )
                        } ?: run {
                            _loginState.value = LoginState.Error("Empty response")
                        }
                    }
                    else -> {
                        val errorMsg = response.errorBody()?.string() ?: "Unknown error"
                        _loginState.value = LoginState.Error(errorMsg)
                    }
                }
            } catch (e: IOException) {
                _loginState.value = LoginState.Error("Connection error")
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Unexpected error: ${e.localizedMessage}")
            }
        }
    }
}