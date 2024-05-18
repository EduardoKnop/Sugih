package com.example.sugihpersonalfinances.ui.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.sugihpersonalfinances.R
import com.example.sugihpersonalfinances.ui.theme.NeutralGoogleBackground
import com.example.sugihpersonalfinances.ui.theme.NeutralGoogleFont
import com.example.sugihpersonalfinances.ui.theme.robotoFamily

@Composable
fun SignInButton(
    text: String,
    icon: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonColors(
            NeutralGoogleBackground,
            NeutralGoogleFont,
            NeutralGoogleBackground,
            NeutralGoogleFont
        ),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(100),
        modifier = modifier
            .heightIn(40.dp)
            .widthIn(183.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 10.dp)
        )
        Text(
            text = text,
            fontFamily = robotoFamily,
            fontSize = TextUnit(14f, TextUnitType.Sp),
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun SignInButtonGooglePreview() {
    SignInButton(
        text = "Sign in with Google",
        icon = painterResource(id = R.drawable.google_icon),
        onClick = {},
        modifier = Modifier
            .height(40.dp)
            .width(183.dp)
    )
}