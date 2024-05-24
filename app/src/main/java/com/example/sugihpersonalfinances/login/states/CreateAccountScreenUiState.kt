package com.example.sugihpersonalfinances.login.states

data class CreateAccountScreenUiState(
    val nicknameText: String = "",
    val onNicknameTextChange: (String) -> Unit = {},
    val emailText: String = "",
    val onEmailTextChange: (String) -> Unit = {},
    val passwordText: String = "",
    val onPasswordTextChange: (String) -> Unit = {},
    val onPasswordClickChange: (Boolean) -> Unit = {},
    val passwordConfirmText: String = "",
    val onPasswordConfirmTextChange: (String) -> Unit = {},
    val onPasswordConfirmClickChange: (Boolean) -> Unit = {}
) {

    fun isAccountInfoValid(): Boolean = !isAnyTextEmpty() && isEmailValid()
            && isPasswordValid() && isPasswordAndPasswordConfirmEquals()

    private fun isAnyTextEmpty(): Boolean = isNicknameEmpty() || isEmailEmpty()
            || isPasswordEmpty() || isPasswordConfirmEmpty()

    fun isNicknameEmpty(): Boolean = nicknameText.isBlank()

    fun isEmailEmpty(): Boolean = emailText.isBlank()

    fun isPasswordEmpty(): Boolean = passwordText.isBlank()

    fun isPasswordConfirmEmpty(): Boolean = passwordConfirmText.isBlank()

    fun isEmailValid(): Boolean = emailText.contains(Regex("^\\p{Print}+@\\p{Alnum}+.com"))
            && emailText.count { it == '@' } == 1

    fun isPasswordValid(): Boolean = passwordText.length >= 6
                && passwordText.contains(Regex("\\p{Lower}+"))
                && passwordText.contains(Regex("\\p{Upper}+"))
                && passwordText.contains(Regex("\\d+"))
                && !passwordText.contains(Regex("\\W"))

    fun isPasswordAndPasswordConfirmEquals(): Boolean = passwordText == passwordConfirmText

}