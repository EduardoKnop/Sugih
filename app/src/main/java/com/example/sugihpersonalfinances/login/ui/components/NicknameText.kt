package com.example.sugihpersonalfinances.login.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun NicknameText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(100),
        label = {
            Text(
                text = "Nickname",
                fontFamily = robotoFamily
            )
        },
        placeholder = {
            Text(
                text = "Insert your Nickname",
                fontFamily = robotoFamily
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = ""
            )
        },
        modifier = modifier.horizontalScroll(rememberScrollState())
    )
}

@Preview(showBackground = true)
@Composable
private fun NicknameTextPreview() {
    NicknameText(value = "", onValueChange = {})
}

@Preview(showBackground = true)
@Composable
private fun NicknameTextWithValuePreview() {
    NicknameText(value = "User Nickname", onValueChange = {})
}