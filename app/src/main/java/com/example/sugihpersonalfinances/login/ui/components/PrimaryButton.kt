package com.example.sugihpersonalfinances.login.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.ui.theme.IndigoDye
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = "Log In",
) {
    FilledTonalButton(
        onClick = onClick,
        shape = RoundedCornerShape(100),
        colors = ButtonColors(
            IndigoDye, Color.White, IndigoDye, Color.White
        ),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 12.dp),
        modifier = modifier
            .heightIn(40.dp)
            .widthIn(80.dp)
    ) {
        Text(
            text = text,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = TextUnit(14f, TextUnitType.Sp),
            maxLines = 1,
            overflow = TextOverflow.Visible
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginButtonPreview() {
    PrimaryButton(onClick = {})
}