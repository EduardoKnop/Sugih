package com.example.sugihpersonalfinances.login.states

data class CreateAccountScreenUiState(
    val nicknameText: String = "",
    val onNicknameTextChange: (String) -> Unit = {},
    val emailText: String = "",
    val onEmailTextChange: (String) -> Unit = {},
    val passwordText: String = "",
    val onPasswordTextChange: (String) -> Unit = {},
    val passwordConfirmText: String = "",
    val onPasswordConfirmTextChange: (String) -> Unit = {}
) {
}