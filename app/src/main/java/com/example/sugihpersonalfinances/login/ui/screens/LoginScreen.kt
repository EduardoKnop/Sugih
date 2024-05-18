package com.example.sugihpersonalfinances.ui.login.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.R
import com.example.sugihpersonalfinances.ui.login.components.EmailText
import com.example.sugihpersonalfinances.ui.login.components.SecondaryButton
import com.example.sugihpersonalfinances.ui.login.components.PrimaryButton
import com.example.sugihpersonalfinances.ui.login.components.PasswordText
import com.example.sugihpersonalfinances.ui.login.components.SignInButton
import com.example.sugihpersonalfinances.ui.theme.NeutralGoogleBackground
import com.example.sugihpersonalfinances.ui.theme.NeutralGoogleFont
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun LoginScreen() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
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
                    icon = painterResource(id = R.drawable.google_icon),
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                SignInButton(
                    text = "Continue with Facebook",
                    icon = painterResource(id = R.drawable.facebook_icon),
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                SignInButton(
                    text = "Continue as Guest",
                    icon = painterResource(id = R.drawable.account_icon),
                    onClick = { /*TODO*/ },
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
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                PasswordText(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                PrimaryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
                SecondaryButton(
                    onClick = { /*TODO*/ },
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
    LoginScreen()
}