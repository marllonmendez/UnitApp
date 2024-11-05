package com.marllonmendez.conversordeunidades.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryDark
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryLight

@Composable
fun converterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    unitValue: String,
    onUnitValueChange: (String) -> Unit,
    options: List<String> = listOf("Centímetros", "Metros", "Quilômetros", "Milhas"),
    onFocus: () -> Unit
) {
    val unitLabel = when (unitValue) {
        "Centímetros" -> "Cm"
        "Metros" -> "M"
        "Quilômetros" -> "Km"
        "Milhas" -> "Mi"
        else -> unitValue
    }

    val colorGradient = Brush.horizontalGradient(
        colors = listOf(
            PrimaryDark,
            PrimaryLight
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(107.dp)
            .background(
                colorGradient,
                shape = MaterialTheme.shapes.small
            )
            .padding(12.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelLarge,
                )

                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    // aqui
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .onFocusChanged { onFocus() },
                    readOnly = true,
                    textStyle = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        cursorColor = Color.White,
                    )

                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = unitLabel,
                    style = MaterialTheme.typography.labelMedium,
                )

                var expanded by remember { mutableStateOf(false) }

                Box {
                    TextButton(
                        onClick = { expanded = !expanded },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = unitValue,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        options.forEach { unit ->
                            DropdownMenuItem(
                                text = { Text(unit) },
                                onClick = {
                                    onUnitValueChange(unit)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}