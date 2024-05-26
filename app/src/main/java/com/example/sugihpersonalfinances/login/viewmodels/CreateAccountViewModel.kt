package com.example.sugihpersonalfinances.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sugihpersonalfinances.login.authentication.UserAuthenticator
import com.example.sugihpersonalfinances.login.enums.CreateAccountStatus
import com.example.sugihpersonalfinances.login.exceptions.EmptyEmailException
import com.example.sugihpersonalfinances.login.exceptions.EmptyNicknameException
import com.example.sugihpersonalfinances.login.exceptions.EmptyPasswordException
import com.example.sugihpersonalfinances.login.exceptions.InvalidEmailException
import com.example.sugihpersonalfinances.login.exceptions.InvalidPasswordException
import com.example.sugihpersonalfinances.login.exceptions.PasswordConfirmationNotEqualsException
import com.example.sugihpersonalfinances.login.states.CreateAccountScreenUiState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateAccountViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<CreateAccountScreenUiState> =
        MutableStateFlow(CreateAccountScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    var status: CreateAccountStatus = CreateAccountStatus.NOT_INITIALIZED
        private set

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onNicknameTextChange = {
                    if (status != CreateAccountStatus.STARTED)
                        _uiState.value = _uiState.value.copy(nicknameText = it)
                },
                onEmailTextChange = {
                    if (status != CreateAccountStatus.STARTED)
                        _uiState.value = _uiState.value.copy(emailText = it)
                },
                onPasswordTextChange = {
                    if (status != CreateAccountStatus.STARTED)
                        _uiState.value = _uiState.value.copy(passwordText = it)
                },
                onPasswordConfirmTextChange = {
                    if (status != CreateAccountStatus.STARTED)
                        _uiState.value = _uiState.value.copy(passwordConfirmText = it)
                }
            )
        }
    }

    fun createAccountWithEmail(): Task<AuthResult> {
        _uiState.value.run {
            status = CreateAccountStatus.STARTED

            if (!isAccountInfoValid()) {
                status = CreateAccountStatus.INTERNAL_ERROR

                if (isNicknameEmpty()) throw EmptyNicknameException()

                if (isEmailEmpty()) throw EmptyEmailException()

                if (isPasswordEmpty() || isPasswordConfirmEmpty())
                    throw EmptyPasswordException(
                        "Password or Confirm Password Empty: " +
                                "Password = $passwordText, " +
                                "ConfirmPassword = $passwordConfirmText"
                    )

                if (!isEmailValid()) throw InvalidEmailException("Invalid Email: $emailText")

                if (!isPasswordAndPasswordConfirmEquals())
                    throw PasswordConfirmationNotEqualsException(
                        "Password and Confirm Password Not Equals: " +
                                "Password = $passwordText, " +
                                "ConfirmPassword = $passwordConfirmText"
                    )

                if (!isPasswordValid())
                    throw InvalidPasswordException("Invalid Password: $passwordText")
            }

            val profileUpdates = userProfileChangeRequest {
                displayName = nicknameText
            }

            val pendingTask = UserAuthenticator.createAccountWithEmail(emailText, passwordText)
                .addOnCompleteListener { result ->
                    status = if (result.isSuccessful) {
                        result.result.user!!.updateProfile(profileUpdates)
                        CreateAccountStatus.SUCCESS
                    } else {
                        CreateAccountStatus.FIREBASE_ERROR
                    }
                }

            return pendingTask
        }
    }

}