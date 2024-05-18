package com.example.sugihpersonalfinances.login.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sugihpersonalfinances.R
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun EmailText(
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
                painter = painterResource(id = R.drawable.email_icon),
                contentDescription = ""
            )
        },
        modifier = modifier.horizontalScroll(rememberScrollState())
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