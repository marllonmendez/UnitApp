package com.marllonmendez.conversordeunidades.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.marllonmendez.conversordeunidades.components.numericKeyboard
import com.marllonmendez.conversordeunidades.components.converterField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun main(controller: NavController) {
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
        var activeField by remember { mutableStateOf<ActiveField?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 100.dp, 20.dp, 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            var fromValue by remember { mutableStateOf("0") }
            var toValue by remember { mutableStateOf("0") }
            var fromUnit by remember { mutableStateOf("Centímetros") }
            var toUnit by remember { mutableStateOf("Metros") }

            converterField(
                label = "De",
                value = fromValue,
                onValueChange = { fromValue = it },
                unitValue = fromUnit,
                onUnitValueChange = { fromUnit = it },
                onFocus = { activeField = ActiveField.From}
            )

            converterField(
                label = "Para",
                value = toValue,
                onValueChange = { toValue = it },
                unitValue = toUnit,
                onUnitValueChange = { toUnit = it },
                onFocus = { activeField = ActiveField.To}
            )

            numericKeyboard(
                onNumberClick = { number ->
                    when (activeField) {
                        ActiveField.From -> fromValue += number
                        ActiveField.To -> toValue += number
                        null -> {}
                    }
                },
                onComma = {
                    when (activeField) {
                        ActiveField.From -> {
                            // Adiciona a vírgula se não estiver vazia e não houver uma já presente
                            if (!fromValue.contains(",")) {
                                fromValue += ","
                            }
                        }
                        ActiveField.To -> {
                            // Adiciona a vírgula se não estiver vazia e não houver uma já presente
                            if (!toValue.contains(",")) {
                                toValue += ","
                            }
                        }
                        null -> {}
                    }
                },
                onClear = {
                    fromValue = "0"
                    toValue = "0"
                },
                onBackspace = {
                    when (activeField) {
                        ActiveField.From -> if (fromValue.isNotEmpty()) {
                            fromValue = fromValue.dropLast(1)
                        }
                        ActiveField.To -> if (toValue.isNotEmpty()) {
                            toValue = toValue.dropLast(1)
                        }
                        null -> {}
                    }
                }
            )

        }
    }
}

enum class ActiveField {
    From,
    To
}