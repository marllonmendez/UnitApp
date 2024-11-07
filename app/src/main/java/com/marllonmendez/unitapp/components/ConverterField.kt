package com.marllonmendez.unitapp.components

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

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

import com.marllonmendez.unitapp.enums.UnitLabel
import com.marllonmendez.unitapp.enums.UnitType
import com.marllonmendez.unitapp.ui.theme.PrimaryDark
import com.marllonmendez.unitapp.ui.theme.PrimaryLight

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
            .height(110.dp)
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
                    value = formatNumber(value),
                    onValueChange = {
                        onValueChange(formatNumber(it))
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
                        textAlign = TextAlign.End,
                    ),
                    shape = MaterialTheme.shapes.small,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
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
                            .padding(horizontal = 20.dp)
                            .background(MaterialTheme.colorScheme.secondary),
                        containerColor = MaterialTheme.colorScheme.secondary,
                        shape = MaterialTheme.shapes.small,
                    ) {
                        options.forEach { unit ->
                            DropdownMenuItem(
                                text = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Text(
                                            unit,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.titleSmall,
                                            modifier = Modifier.align(Alignment.Center)
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

private fun formatNumber(value: String): String {
    val formatter = NumberFormat.getInstance(Locale("pt", "BR")) as DecimalFormat
    val cleanInput = value.replace(".", "")

    if (cleanInput.isEmpty()) return value

    try {
        val decimal = value.indexOf(",")
        return if (decimal != -1) {
            val integerPart = cleanInput.substring(0, decimal)
            val decimalPart = cleanInput.substring(decimal)

            val number = integerPart.toLong()
            val formattedInteger = formatter.format(number)

            "$formattedInteger$decimalPart"
        } else {
            val number = cleanInput.toLong()
            formatter.format(number)
        }
    } catch (e: NumberFormatException) {
        return value
    }
}
