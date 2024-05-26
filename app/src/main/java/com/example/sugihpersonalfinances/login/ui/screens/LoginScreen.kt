package com.example.sugihpersonalfinances.login.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.R
import com.example.sugihpersonalfinances.login.states.LoginScreenUiState
import com.example.sugihpersonalfinances.login.ui.components.EmailText
import com.example.sugihpersonalfinances.login.ui.components.PasswordText
import com.example.sugihpersonalfinances.login.ui.components.PrimaryButton
import com.example.sugihpersonalfinances.login.ui.components.SecondaryButton
import com.example.sugihpersonalfinances.login.ui.components.SignInButton
import com.example.sugihpersonalfinances.login.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier,
    onContinueWithGoogleClick: () -> Unit = {},
    onContinueWithFacebookClick: () -> Unit = {},
    onContinueAsGuestClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {

    val state by viewModel.uiState.collectAsState()
    LoginScreen(
        state = state,
        modifier = modifier,
        onContinueWithGoogleClick = {
            onContinueWithGoogleClick()
        },
        onContinueWithFacebookClick = { /*TODO*/ },
        onContinueAsGuestClick = {
            viewModel.logInAnonymously().addOnCompleteListener {
                onContinueAsGuestClick()
            }
        },
        onLoginClick = {
            try {
                viewModel.logInWithEmail().addOnCompleteListener {
                    onLoginClick()
                }
            } catch (e: IllegalArgumentException) {
                Log.i("CreateAccountScreen", "Invalid Data: ${e.message}")
                onLoginClick()
            }
        },
        onForgotPasswordClick = { /*TODO*/ }
    )

}

@Composable
fun LoginScreen(
    state: LoginScreenUiState,
    modifier: Modifier = Modifier,
    onContinueWithGoogleClick: () -> Unit = {},
    onContinueWithFacebookClick: () -> Unit = {},
    onContinueAsGuestClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.LightGray) //TODO: Add a Blur Background
    ) {
        OutlinedCard(
            border = BorderStroke(1.dp, Color.LightGray),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Continue",
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                SignInButton(
                    text = "Continue with Google",
                    icon = ImageVector.vectorResource(id = R.drawable.google_icon),
                    onClick = onContinueWithGoogleClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                SignInButton(
                    text = "Continue with Facebook",
                    icon = ImageVector.vectorResource(id = R.drawable.facebook_icon),
                    onClick = onContinueWithFacebookClick,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                SignInButton(
                    text = "Continue as Guest",
                    icon = Icons.Rounded.AccountCircle,
                    onClick = onContinueAsGuestClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 16.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = "Log In with Email",
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                EmailText(
                    value = state.emailText,
                    onValueChange = state.onEmailTextChange,
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordText(
                    value = state.passwordText,
                    onValueChange = state.onPasswordTextChange,
                    modifier = Modifier.fillMaxWidth()
                )
                PrimaryButton(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                SecondaryButton(
                    onClick = onForgotPasswordClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(state = LoginScreenUiState())
}