package com.example.sugihpersonalfinances.login.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sugihpersonalfinances.login.authentication.SignInResult
import com.example.sugihpersonalfinances.login.authentication.UserAuthenticator
import com.example.sugihpersonalfinances.login.enums.CreateAccountStatus
import com.example.sugihpersonalfinances.login.exceptions.EmptyEmailException
import com.example.sugihpersonalfinances.login.exceptions.EmptyPasswordException
import com.example.sugihpersonalfinances.login.exceptions.InvalidEmailException
import com.example.sugihpersonalfinances.login.exceptions.InvalidPasswordException
import com.example.sugihpersonalfinances.login.states.LoginScreenUiState
import com.facebook.AccessToken
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<LoginScreenUiState> =
        MutableStateFlow(LoginScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    var status: CreateAccountStatus = CreateAccountStatus.NOT_INITIALIZED
        private set

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

    fun logInWithEmail(): Task<AuthResult> {
        _uiState.value.run {
            status = CreateAccountStatus.STARTED

            if (!isAccountInfoValid()) {
                status = CreateAccountStatus.INTERNAL_ERROR

                if (isEmailEmpty()) throw EmptyEmailException()

                if (isPasswordEmpty())
                    throw EmptyPasswordException(
                        "Password or Confirm Password Empty: Password = $passwordText"
                    )

                if (!isEmailValid()) throw InvalidEmailException("Invalid Email: $emailText")

                if (!isPasswordValid())
                    throw InvalidPasswordException("Invalid Password: $passwordText")
            }

            val pendingTask = UserAuthenticator.logInWithEmail(emailText, passwordText)
                .addOnCompleteListener { result ->
                    status = if (result.isSuccessful) CreateAccountStatus.SUCCESS
                    else CreateAccountStatus.FIREBASE_ERROR
                }

            return pendingTask
        }
    }

    fun logInAnonymously(): Task<AuthResult> {
        _uiState.value.run {
            status = CreateAccountStatus.STARTED

            val pendingTask = UserAuthenticator.logInAnonymously()
                .addOnCompleteListener { result ->
                    status = if (result.isSuccessful) CreateAccountStatus.SUCCESS
                    else CreateAccountStatus.FIREBASE_ERROR
                }

            return pendingTask
        }
    }

    fun logInWithFacebook(token: AccessToken): Task<AuthResult> {
        _uiState.value.run {
            status = CreateAccountStatus.STARTED

            val pendingTask = UserAuthenticator.logInWithFacebook(token)
                .addOnCompleteListener { result ->
                    status = if (result.isSuccessful) CreateAccountStatus.SUCCESS
                    else CreateAccountStatus.FIREBASE_ERROR
                }

            return pendingTask
        }
    }

    fun onSignInResult(result: SignInResult) {

    }

}