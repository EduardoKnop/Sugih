package com.example.sugihpersonalfinances.login.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sugihpersonalfinances.login.authentication.UserAuthenticator
import com.example.sugihpersonalfinances.login.destination.Destination
import com.example.sugihpersonalfinances.login.exceptions.EmptyEmailException
import com.example.sugihpersonalfinances.login.exceptions.EmptyNicknameException
import com.example.sugihpersonalfinances.login.exceptions.EmptyPasswordException
import com.example.sugihpersonalfinances.login.exceptions.InvalidEmailException
import com.example.sugihpersonalfinances.login.exceptions.InvalidPasswordException
import com.example.sugihpersonalfinances.login.exceptions.PasswordConfirmationNotEqualsException
import com.example.sugihpersonalfinances.login.states.WelcomeScreenUiState
import com.example.sugihpersonalfinances.login.ui.screens.CreateAccountScreen
import com.example.sugihpersonalfinances.login.ui.screens.LoginScreen
import com.example.sugihpersonalfinances.login.ui.screens.WelcomeScreen
import com.example.sugihpersonalfinances.login.viewmodels.CreateAccountViewModel
import com.example.sugihpersonalfinances.login.viewmodels.LoginViewModel
import com.example.sugihpersonalfinances.ui.theme.IndigoDye
import com.example.sugihpersonalfinances.ui.theme.SugihPersonalFinancesTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = UserAuthenticator.auth

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()

            val snackbarHostState = remember { SnackbarHostState() }

            val loginViewModel: LoginViewModel by viewModels()
            val createAccountViewModel: CreateAccountViewModel by viewModels()

            Screen(snackbarHostState = snackbarHostState) {
                NavHost(
                    navController = navController,
                    startDestination = Destination.Welcome.route
                ) {
                    composable(Destination.Welcome.route) {
                        WelcomeScreen(
                            state = WelcomeScreenUiState(
                                onLoginClick = {
                                    navController.navigate(Destination.Login.route)
                                },
                                onCreateAccountClick = {
                                    navController.navigate(Destination.CreateAccount.route)
                                }
                            )
                        )
                    }
                    composable(Destination.Login.route) {
                        LoginScreen(
                            viewModel = loginViewModel
                        )
                    }
                    composable(Destination.CreateAccount.route) {
                        CreateAccountScreen(
                            viewModel = createAccountViewModel,
                            onCreateSuccess = {
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = "Success", actionLabel = "Ok"
                                    )

                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {

                                        }

                                        SnackbarResult.Dismissed -> {

                                        }
                                    }
                                }
                            },
                            onCreateFailure = { exception ->
                                scope.launch {
                                    val message = when (exception) {
                                        is EmptyNicknameException ->
                                            "Please add your Nickname to Create Account"

                                        is EmptyEmailException ->
                                            "Please add your Email to Create Account"

                                        is EmptyPasswordException ->
                                            "Please add your Password to Create Account"

                                        is PasswordConfirmationNotEqualsException ->
                                            "Confirm Password and Password does not Match"

                                        is InvalidEmailException ->
                                            "Invalid Email"

                                        is InvalidPasswordException ->
                                            "Invalid Password"

                                        is FirebaseAuthUserCollisionException -> {
                                            "Email already Exists"
                                        }

                                        else -> {
                                            Log.d("LoginActivity", "" + exception.message)
                                            "An Error Occurred"
                                        }
                                    }
                                    val result = snackbarHostState.showSnackbar(
                                        message = message,
                                        actionLabel = "Ok",
                                        duration = SnackbarDuration.Short
                                    )

                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {

                                        }

                                        SnackbarResult.Dismissed -> {

                                        }
                                    }
                                }
                            }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Screen(
    snackbarHostState: SnackbarHostState,
    content: @Composable () -> Unit = {}
) {
    SugihPersonalFinancesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = IndigoDye
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }

        }
    }
}