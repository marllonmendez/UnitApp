package com.marllonmendez.conversordeunidades.components

import com.marllonmendez.conversordeunidades.ui.theme.PrimaryDark

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = PrimaryDark,
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(width = 0.dp, color = Color.Unspecified),
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.surface,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
@Preview
private fun ButtonPreview() {
    button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 20.dp, 20.dp, 0.dp)
            .height(50.dp),
        label = "title"
    )
}