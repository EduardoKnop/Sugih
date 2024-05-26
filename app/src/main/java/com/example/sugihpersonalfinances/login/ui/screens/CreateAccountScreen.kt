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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.login.enums.CreateAccountStatus
import com.example.sugihpersonalfinances.login.states.CreateAccountScreenUiState
import com.example.sugihpersonalfinances.login.ui.components.EmailText
import com.example.sugihpersonalfinances.login.ui.components.NicknameText
import com.example.sugihpersonalfinances.login.ui.components.PasswordText
import com.example.sugihpersonalfinances.login.ui.components.PrimaryButton
import com.example.sugihpersonalfinances.login.viewmodels.CreateAccountViewModel

@Composable
fun CreateAccountScreen(
    viewModel: CreateAccountViewModel,
    modifier: Modifier = Modifier,
    onCreateAccountClick: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    CreateAccountScreen(
        state = state,
        modifier = modifier,
        onCreateAccountClick = {
            try {
                viewModel.createAccountWithEmail().addOnCompleteListener {
                    onCreateAccountClick()
                }
            } catch (e: IllegalArgumentException) {
                Log.i("CreateAccountScreen", "Invalid Data: ${e.message}")
                onCreateAccountClick()
            }
        }
    )

}

@Composable
fun CreateAccountScreen(
    state: CreateAccountScreenUiState,
    modifier: Modifier = Modifier,
    onCreateAccountClick: () -> Unit = {}
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
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Account",
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                NicknameText(
                    value = state.nicknameText,
                    onValueChange = state.onNicknameTextChange,
                    modifier = Modifier.fillMaxWidth()
                )
                EmailText(
                    value = state.emailText,
                    onValueChange = state.onEmailTextChange,
                    isError = !state.isEmailValid() && !state.isEmailEmpty(),
                    errorText = "Enter the email address in the format: someone@example.com",
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordText(
                    value = state.passwordText,
                    onValueChange = state.onPasswordTextChange,
                    isError = !state.isPasswordValid() && !state.isPasswordEmpty(),
                    errorText = """Your password must contain:
                            |6 or More Digits
                            |An Uppercase Letter
                            |A Lowercase Letter
                            |A Number
                        """.trimMargin(),
                    keyboardImeAction = ImeAction.Next,
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordText(
                    value = state.passwordConfirmText,
                    onValueChange = state.onPasswordConfirmTextChange,
                    isError = !state.isPasswordAndPasswordConfirmEquals()
                            && !state.isPasswordConfirmEmpty(),
                    errorText = "Password and Confirm Password must be Equals",
                    labelText = "Confirm Password",
                    placeholderText = "Insert your Password Again",
                    modifier = Modifier.fillMaxWidth()
                )

                PrimaryButton(
                    onClick = onCreateAccountClick,
                    text = "Create Account",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CreateAccountScreenPreview() {
    CreateAccountScreen(state = CreateAccountScreenUiState())
}