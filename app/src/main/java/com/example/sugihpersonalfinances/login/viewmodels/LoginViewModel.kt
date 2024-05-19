package com.example.sugihpersonalfinances.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sugihpersonalfinances.login.states.LoginScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<LoginScreenUiState> =
        MutableStateFlow(LoginScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailTextChange = {
                    _uiState.value = _uiState.value.copy(
                        emailText = it
                    )
                },
                onPasswordTextChange = {
                    _uiState.value = _uiState.value.copy(
                        passwordText = it
                    )
                }
            )
        }
    }

}