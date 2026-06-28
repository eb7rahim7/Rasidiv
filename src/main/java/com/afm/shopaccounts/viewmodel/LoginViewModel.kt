package com.afm.shopaccounts.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(pin: String) {
        _loginState.value = LoginState.Loading
        
        // التحقق من الرمز الافتراضي
        if (pin == "1234") {
            _loginState.value = LoginState.Success
        } else {
            _loginState.value = LoginState.Error("رمز الدخول غير صحيح")
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}
