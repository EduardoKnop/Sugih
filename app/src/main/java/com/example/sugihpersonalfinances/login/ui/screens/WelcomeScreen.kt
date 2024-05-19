package com.example.sugihpersonalfinances.login.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.login.states.WelcomeScreenUiState
import com.example.sugihpersonalfinances.login.ui.components.PrimaryButton
import com.example.sugihpersonalfinances.login.ui.components.SecondaryButton

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    state: WelcomeScreenUiState = WelcomeScreenUiState(onLoginClick = {}, onCreateAccountClick = {})
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray) //TODO: Add a Blur Background
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.75f)
        )
        PrimaryButton(
            onClick = state.onLoginClick,
            modifier = Modifier
                .requiredHeightIn(40.dp)
                .fillMaxWidth(0.8f)
        )
        SecondaryButton(
            onClick = state.onCreateAccountClick,
            text = "Create Account",
            modifier = Modifier
                .requiredHeightIn(40.dp)
                .fillMaxWidth(0.8f)
                .padding(top = 4.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}