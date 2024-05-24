package com.example.sugihpersonalfinances.login.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun EmailText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    EmailText(
        value = value,
        onValueChange = onValueChange,
        isError = false,
        errorText = "",
        modifier = modifier
    )
}

@Composable
fun EmailText(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    errorText: String,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(100),
        label = {
            Text(
                text = "Email",
                fontFamily = robotoFamily
            )
        },
        placeholder = {
            Text(
                text = "Insert your Email Address",
                fontFamily = robotoFamily
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = ""
            )
        },
        isError = if (!isFocused) isError else false,
        supportingText = {
            if (isError && !isFocused)
                Text(text = errorText)
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .onFocusChanged { isFocused = it.isFocused }
    )
}

@Preview(showBackground = true)
@Composable
private fun EmailTextPreview() {
    EmailText(value = "", onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun EmailTextWithValuePreview() {
    EmailText(value = "email@email.com", onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun EmailTextErrorPreview() {
    EmailText(value = "", onValueChange = {}, isError = true, errorText = "Error")
}

@Preview(showBackground = true)
@Composable
private fun EmailTextWithValueErrorPreview() {
    EmailText(value = "email@email.com", onValueChange = {}, isError = true, errorText = "Error")
}