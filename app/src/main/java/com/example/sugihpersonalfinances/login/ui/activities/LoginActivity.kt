package com.example.sugihpersonalfinances.login.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sugihpersonalfinances.login.ui.screens.LoginScreen
import com.example.sugihpersonalfinances.ui.theme.IndigoDye
import com.example.sugihpersonalfinances.ui.theme.SugihPersonalFinancesTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SugihPersonalFinancesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = IndigoDye
                ) {
                    LoginScreen()
                }
            }
        }
    }
}