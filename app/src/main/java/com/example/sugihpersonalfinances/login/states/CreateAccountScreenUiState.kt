package com.example.sugihpersonalfinances.login.states

import com.example.sugihpersonalfinances.login.exceptions.EmptyEmailException
import com.example.sugihpersonalfinances.login.exceptions.EmptyNicknameException
import com.example.sugihpersonalfinances.login.exceptions.EmptyPasswordException
import com.example.sugihpersonalfinances.login.exceptions.InvalidEmailException
import com.example.sugihpersonalfinances.login.exceptions.InvalidPasswordException
import com.example.sugihpersonalfinances.login.exceptions.PasswordConfirmationNotEqualsException

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

    fun isAnyTextEmpty(): Boolean = isNicknameEmpty() || isEmailEmpty()
            || isPasswordEmpty() || isPasswordConfirmEmpty()

    private fun isNicknameEmpty(): Boolean = nicknameText.isBlank()

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

    fun createAccountError(): Exception {
        if (isNicknameEmpty()) {
            return EmptyNicknameException()
        } else if (isEmailEmpty()) {
            return EmptyEmailException()
        } else if (isPasswordEmpty() || isPasswordConfirmEmpty()) {
            return EmptyPasswordException(
                "Password or Confirm Password Empty: " +
                        "Password = $passwordText, " +
                        "ConfirmPassword = $passwordConfirmText"
            )
        } else if (!isEmailValid()) {
            return InvalidEmailException("Invalid Email: $emailText")
        } else if (!isPasswordAndPasswordConfirmEquals()) {
            return PasswordConfirmationNotEqualsException(
                "Password and Confirm Password Not Equals: " +
                        "Password = $passwordText, " +
                        "ConfirmPassword = $passwordConfirmText"
            )
        } else if (!isPasswordValid()) {
            return InvalidPasswordException("Invalid Password: $passwordText")
        }

        return Exception("WTF! Nickname: $nicknameText, Email: $emailText, " +
                "Password: $passwordText, Confirm Password: $passwordConfirmText")
    }

}