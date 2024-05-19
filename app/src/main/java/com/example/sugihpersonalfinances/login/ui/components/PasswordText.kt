package com.example.sugihpersonalfinances.login.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.sugihpersonalfinances.R
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun PasswordText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelText: String = "Password",
    placeholderText: String = "Insert your Password",
    keyboardImeAction: ImeAction = ImeAction.Done
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(100),
        label = {
            Text(
                text = labelText,
                fontFamily = robotoFamily
            )
        },
        placeholder = {
            Text(
                text = placeholderText,
                fontFamily = robotoFamily
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.password_icon),
                contentDescription = ""
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = keyboardImeAction
        ),
        modifier = modifier.horizontalScroll(rememberScrollState())
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextPreview() {
    PasswordText(value = "", onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextWithValuePreview() {
    PasswordText("Password123", onValueChange = {})
}