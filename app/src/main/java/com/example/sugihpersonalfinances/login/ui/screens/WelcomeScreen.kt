package com.example.sugihpersonalfinances.login.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.login.ui.components.PrimaryButton
import com.example.sugihpersonalfinances.login.ui.components.SecondaryButton

@Composable
fun WelcomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.8f)
        )
        PrimaryButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .heightIn(40.dp)
                .fillMaxWidth(0.8f)
        )
        SecondaryButton(
            onClick = { /*TODO*/ },
            text = "Create Account",
            modifier = Modifier
                .heightIn(40.dp)
                .fillMaxWidth(0.8f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}