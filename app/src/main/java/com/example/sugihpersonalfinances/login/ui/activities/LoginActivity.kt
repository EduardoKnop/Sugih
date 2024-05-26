package com.example.sugihpersonalfinances.login.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sugihpersonalfinances.login.authentication.GoogleAuthUiClient
import com.example.sugihpersonalfinances.login.destination.Destination
import com.example.sugihpersonalfinances.login.enums.CreateAccountStatus
import com.example.sugihpersonalfinances.login.states.WelcomeScreenUiState
import com.example.sugihpersonalfinances.login.ui.screens.CreateAccountScreen
import com.example.sugihpersonalfinances.login.ui.screens.LoginScreen
import com.example.sugihpersonalfinances.login.ui.screens.WelcomeScreen
import com.example.sugihpersonalfinances.login.viewmodels.CreateAccountViewModel
import com.example.sugihpersonalfinances.login.viewmodels.LoginViewModel
import com.example.sugihpersonalfinances.ui.theme.IndigoDye
import com.example.sugihpersonalfinances.ui.theme.SugihPersonalFinancesTheme
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import java.util.Arrays

class LoginActivity : ComponentActivity() {

    private val callbackManager = CallbackManager.Factory.create()
    private val loginManager = LoginManager.getInstance()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(oneTapClient = Identity.getSignInClient(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel: LoginViewModel by viewModels()
        val createAccountViewModel: CreateAccountViewModel by viewModels()

        loginManager.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    loginViewModel.logInWithFacebook(result.accessToken)
                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {

                }
            }
        )

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()

            val snackbarHostState = remember { SnackbarHostState() }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            loginViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            Screen(snackbarHostState = snackbarHostState) {
                val context = LocalContext.current

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
                            viewModel = loginViewModel,
                            onLoginClick = {
                                scope.launch {
                                    val message = when (loginViewModel.status) {
                                        CreateAccountStatus.SUCCESS ->
                                            "Success"

                                        CreateAccountStatus.INTERNAL_ERROR ->
                                            "Incorrect Email or Password"

                                        CreateAccountStatus.FIREBASE_ERROR ->
                                            "Incorrect Email or Password"

                                        else -> ""
                                    }

                                    val result = snackbarHostState.showSnackbar(
                                        message = message, actionLabel = "Ok"
                                    )

                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {

                                        }

                                        SnackbarResult.Dismissed -> {

                                        }
                                    }
                                }
                            },
                            onContinueWithGoogleClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            },
                            onContinueWithFacebookClick = {
                                loginManager.logIn(
                                    context as ActivityResultRegistryOwner,
                                    callbackManager,
                                    listOf("email", "public_profile")
                                )
                            }
                        )
                    }
                    composable(Destination.CreateAccount.route) {
                        CreateAccountScreen(
                            viewModel = createAccountViewModel,
                            onCreateAccountClick = {
                                scope.launch {
                                    val message = when (createAccountViewModel.status) {
                                        CreateAccountStatus.SUCCESS ->
                                            "Success"

                                        CreateAccountStatus.INTERNAL_ERROR ->
                                            "Invalid Data! Please Check and Submit Again"

                                        CreateAccountStatus.FIREBASE_ERROR ->
                                            "Connection Error! Try Again Later"

                                        else -> ""
                                    }

                                    val result = snackbarHostState.showSnackbar(
                                        message = message, actionLabel = "Ok"
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