package com.example.sugihpersonalfinances.login.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sugihpersonalfinances.login.destination.Destination
import com.example.sugihpersonalfinances.login.states.WelcomeScreenUiState
import com.example.sugihpersonalfinances.login.ui.screens.CreateAccountScreen
import com.example.sugihpersonalfinances.login.ui.screens.LoginScreen
import com.example.sugihpersonalfinances.login.ui.screens.WelcomeScreen
import com.example.sugihpersonalfinances.login.viewmodels.CreateAccountViewModel
import com.example.sugihpersonalfinances.ui.theme.IndigoDye
import com.example.sugihpersonalfinances.ui.theme.SugihPersonalFinancesTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            SugihPersonalFinancesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = IndigoDye
                ) {
                    val createAccountViewModel: CreateAccountViewModel by viewModels()

                    NavHost(
                        navController = navController,
                        startDestination = Destination.Welcome.route
                    ) {
                        composable(Destination.Welcome.route) {
                            WelcomeScreen(state = WelcomeScreenUiState(
                                onLoginClick = {
                                    navController.navigate(Destination.Login.route)
                                },
                                onCreateAccountClick = {
                                    navController.navigate(Destination.CreateAccount.route)
                                }
                            ))
                        }
                        composable(Destination.Login.route) {
                            LoginScreen()
                        }
                        composable(Destination.CreateAccount.route) {
                            CreateAccountScreen(
                                viewModel = createAccountViewModel,
                                onCreateAccountClick = { /*TODO*/ }
                            )
                        }
                    }
                }
            }
        }
    }
}