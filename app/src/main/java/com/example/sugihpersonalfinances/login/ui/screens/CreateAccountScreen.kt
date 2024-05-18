package com.example.sugihpersonalfinances.login.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.login.ui.components.EmailText
import com.example.sugihpersonalfinances.login.ui.components.NicknameText
import com.example.sugihpersonalfinances.login.ui.components.PasswordText
import com.example.sugihpersonalfinances.login.ui.components.PrimaryButton

@Composable
fun CreateAccountScreen() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
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
                NicknameText(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
                EmailText(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
                PasswordText(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth())
                PasswordText(
                    value = "",
                    labelText = "Confirm Password",
                    placeholderText = "Insert your Password Again",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )

                PrimaryButton(
                    onClick = { /*TODO*/ },
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
    CreateAccountScreen()
}