package com.marllonmendez.conversordeunidades.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.marllonmendez.conversordeunidades.components.numericKeyboard
import com.marllonmendez.conversordeunidades.components.converterField
import com.marllonmendez.conversordeunidades.enums.ActiveField
import com.marllonmendez.conversordeunidades.enums.UnitType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun unitConverterScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {
                    Text(
                        text = "UnitApp",
                        color = MaterialTheme.colorScheme.inverseSurface,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 100.dp, 20.dp, 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var activeField by remember { mutableStateOf<ActiveField?>(null) }
            var fromValue by remember { mutableStateOf("0") }
            var toValue by remember { mutableStateOf("0") }
            var fromUnit by remember { mutableStateOf(UnitType.CENTIMETERS) }
            var toUnit by remember { mutableStateOf(UnitType.METERS) }

            converterField(
                label = "De",
                value = fromValue,
                onValueChange = { fromValue = it },
                unitValue = fromUnit.value,
                onUnitValueChange = { selectedUnit ->
                    fromUnit = UnitType.entries.find { it.value == selectedUnit } ?: fromUnit
                },
                onFocus = { activeField = ActiveField.FROM },
                onEnable = true
            )

            converterField(
                label = "Para",
                value = toValue,
                onValueChange = {},
                unitValue = toUnit.value,
                onUnitValueChange = { selectedUnit ->
                    toUnit = UnitType.entries.find { it.value == selectedUnit } ?: toUnit
                },
                onFocus = {},
                onEnable = false
            )

            numericKeyboard(
                onNumber = { number ->
                    when (activeField) {
                        ActiveField.FROM -> fromValue += number
                        ActiveField.TO -> {}
                        null -> {}
                    }
                },
                onComma = {
                    when (activeField) {
                        ActiveField.FROM -> if (!fromValue.contains(",")) fromValue += ","
                        ActiveField.TO -> {}
                        null -> {}
                    }
                },
                onClear = {
                    fromValue = "0"
                    toValue = "0"
                },
                onBackspace = {
                    when (activeField) {
                        ActiveField.FROM -> if (fromValue.isNotEmpty()) fromValue = fromValue.dropLast(1)
                        ActiveField.TO -> {}
                        null -> {}
                    }
                },
                onExchange = {
                    val tempUnit = fromUnit
                    fromUnit = toUnit
                    toUnit = tempUnit

                    val tempValue = fromValue
                    fromValue = toValue
                    toValue = tempValue
                },
                onEquals = {
                    toValue = convertValue(fromValue, fromUnit, toUnit)
                }
            )

        }
    }
}

private fun convertValue(fromValue: String, fromUnit: UnitType, toUnit: UnitType): String {
    val value = fromValue.replace(",", ".").toDoubleOrNull() ?: return "0"

    val fromUnitValue = when (fromUnit) {
        UnitType.CENTIMETERS -> value / 100
        UnitType.METERS -> value
        UnitType.KILOMETERS -> value * 1000
        UnitType.MILES -> value * 1609.344
    }

    val convertedValue = when (toUnit) {
        UnitType.CENTIMETERS -> fromUnitValue * 100
        UnitType.METERS -> fromUnitValue
        UnitType.KILOMETERS -> fromUnitValue / 1000
        UnitType.MILES -> fromUnitValue / 1609.344
    }

    return convertedValue.toString().replace(".", ",")
}
