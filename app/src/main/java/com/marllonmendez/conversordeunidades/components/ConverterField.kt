package com.marllonmendez.conversordeunidades.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.marllonmendez.conversordeunidades.enums.UnitLabel
import com.marllonmendez.conversordeunidades.enums.UnitType
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryDark
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryLight

@Composable
fun converterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    unitValue: String,
    onUnitValueChange: (String) -> Unit,
    options: List<String> = listOf(
        UnitType.CENTIMETERS.value,
        UnitType.METERS.value,
        UnitType.KILOMETERS.value,
        UnitType.MILES.value,
    ),
    onFocus: () -> Unit,
    onEnable: Boolean
) {
    val unitLabel = when (unitValue) {
        UnitType.CENTIMETERS.value -> UnitLabel.CM.value
        UnitType.METERS.value -> UnitLabel.M.value
        UnitType.KILOMETERS.value -> UnitLabel.KM.value
        UnitType.MILES.value -> UnitLabel.MI.value
        else -> UnitLabel.CM.value
    }

    val colorGradient = Brush.horizontalGradient(
        colors = listOf(
            PrimaryDark,
            PrimaryLight
        )
    )

    var isFocused by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp)
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
                    style = MaterialTheme.typography.titleMedium,
                )

                TextField(
                    value = value,
                    onValueChange = {
                        onValueChange(it)
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            onFocus()
                        },
                    readOnly = true,
                    enabled = onEnable,
                    textStyle = MaterialTheme.typography.labelSmall.copy(
                        color = if (isFocused) Color.Black else Color.White,
                        textAlign = TextAlign.End,
                    ),
                    shape = MaterialTheme.shapes.small,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
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
                    style = MaterialTheme.typography.titleSmall,
                )

                Box {
                    TextButton(
                        onClick = { expanded = !expanded },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = unitValue,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(150.dp)
                            .background(MaterialTheme.colorScheme.secondary),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        // opções: Centímetros, Metros, Quilômetros e Milhas
                        options.forEach { unit ->
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            unit,
                                            textAlign = TextAlign.End,
                                            style = MaterialTheme.typography.titleSmall,
                                            modifier = Modifier.align(Alignment.CenterEnd)
                                        )
                                    }
                                },
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
