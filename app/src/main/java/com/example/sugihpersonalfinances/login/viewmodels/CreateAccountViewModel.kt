package com.example.sugihpersonalfinances.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sugihpersonalfinances.login.states.CreateAccountScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateAccountViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<CreateAccountScreenUiState> =
        MutableStateFlow(CreateAccountScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onNicknameTextChange = {
                    _uiState.value = _uiState.value.copy(
                        nicknameText = it
                    )
                },
                onEmailTextChange = {
                    _uiState.value = _uiState.value.copy(
                        emailText = it
                    )
                },
                onPasswordTextChange = {
                    _uiState.value = _uiState.value.copy(
                        passwordText = it
                    )
                },
                onPasswordConfirmTextChange = {
                    _uiState.value = _uiState.value.copy(
                        passwordConfirmText = it
                    )
                },

            )
        }
    }
}