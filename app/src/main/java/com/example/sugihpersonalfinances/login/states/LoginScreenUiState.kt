package com.example.sugihpersonalfinances.login.states

data class LoginScreenUiState(
    val emailText: String = "",
    val onEmailTextChange: (String) -> Unit = {},
    val passwordText: String = "",
    val onPasswordTextChange: (String) -> Unit = {}
) {

    fun isAnyTextEmpty(): Boolean = isEmailEmpty() || isPasswordEmpty()

    fun isEmailEmpty(): Boolean = emailText.isEmpty()

    fun isPasswordEmpty(): Boolean = passwordText.isEmpty()

    fun isEmailValid(): Boolean = emailText.contains(Regex("^\\p{Print}+@\\p{Alnum}+.com"))
            && emailText.count { it == '@' } == 1

    fun isPasswordValid(): Boolean = passwordText.length >= 6
            && passwordText.contains(Regex("\\p{Lower}+"))
            && passwordText.contains(Regex("\\p{Upper}+"))
            && passwordText.contains(Regex("\\d+"))
            && !passwordText.contains(Regex("\\W"))

}