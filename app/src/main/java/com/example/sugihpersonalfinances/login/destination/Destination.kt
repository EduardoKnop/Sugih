package com.example.sugihpersonalfinances.login.destination

sealed class Destination(val route: String) {
    data object Welcome : Destination("welcome")
    data object Login : Destination("login")
    data object CreateAccount : Destination("create_account")
}