package com.example.sugihpersonalfinances.login.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    PasswordText(
        value = value,
        onValueChange = onValueChange,
        isError = false,
        errorText = "",
        modifier = modifier,
        labelText = labelText,
        placeholderText = placeholderText,
        keyboardImeAction = keyboardImeAction
    )
}

@Composable
fun PasswordText(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    errorText: String,
    modifier: Modifier = Modifier,
    labelText: String = "Password",
    placeholderText: String = "Insert your Password",
    keyboardImeAction: ImeAction = ImeAction.Done,
) {
    var showPassword by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    var wasFocused by remember { mutableStateOf(false) }

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
                imageVector = Icons.Rounded.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            if (showPassword && wasFocused)
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.visibility_off_icon),
                    contentDescription = null,
                    Modifier.clickable {
                        showPassword = !showPassword
                    }
                )
            else if (wasFocused)
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.visibility_on_icon),
                    contentDescription = null,
                    Modifier.clickable {
                        showPassword = !showPassword
                    }
                )
        },
        visualTransformation = if (showPassword) VisualTransformation.None
        else PasswordVisualTransformation(),
        isError = if (!isFocused) isError else false,
        supportingText = {
            if (isError && !isFocused)
                Text(text = errorText)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = keyboardImeAction
        ),
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused

                if (isFocused)
                    wasFocused = true
            }
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

@Preview(showBackground = true)
@Composable
private fun PasswordTextErrorPreview() {
    PasswordText(value = "", onValueChange = {}, isError = true, errorText = "Error")
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextWithValueErrorPreview() {
    PasswordText("Password123", onValueChange = {}, isError = true, errorText = "Error")
}