package com.RCS.my_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.RCS.my_app.api.ApiClient
import com.RCS.my_app.models.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState

    sealed class RegisterState {
        object Idle : RegisterState()
        object Loading : RegisterState()
        data class Success(val email: String) : RegisterState()
        data class Error(val message: String) : RegisterState()
    }

    fun register(name: String, email: String, password: String) {
        _registerState.value = RegisterState.Loading

        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.register(
                    RegisterRequest(
                        name = name,
                        email = email,
                        password = password
                    )
                )

                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success(email)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Error desconocido"
                    _registerState.value = RegisterState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Error de conexión: ${e.message}")
            }
        }
    }
}