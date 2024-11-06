package com.marllonmendez.conversordeunidades.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryDark
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryLight
import com.marllonmendez.conversordeunidades.ui.theme.Tertiary

@Composable
fun numericKeyboard(
    onNumberClick: (String) -> Unit,
    onComma: () -> Unit,
    onClear: () -> Unit,
    onBackspace: () -> Unit,
    onEquals: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            (7..9).forEach { number ->
                numberButton(number = number.toString(), onClick = onNumberClick)
            }
            actionButton(onClick = onClear, label = "C")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            (4..6).forEach { number ->
                numberButton(number = number.toString(), onClick = onNumberClick)
            }
            actionButton(onClick = onBackspace, label = "X")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            (1..3).forEach { number ->
                numberButton(number = number.toString(), onClick = onNumberClick)
            }
            actionButton(onClick = onComma, label = ",")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            numberButton(number = "0", onClick = onNumberClick)
            actionButton(onClick = onEquals, label = "=")
        }
    }
}

@Composable
private fun numberButton(number: String, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(number) },
        modifier = if (number == "0") {
            Modifier
                .width(250.dp)
                .size(64.dp)
        } else {
            Modifier
                .size(64.dp)
        },
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryDark
        )
    ) {
        Text(
            text = number,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 24.sp
            ),
        )
    }
}

@Composable
private fun actionButton(onClick: () -> Unit, label: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(64.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = when (label) {
                "=" -> PrimaryLight
                "C" -> Tertiary
                else -> MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        Text(
            label,
            textAlign = TextAlign.Center,
            style = if (label == "=") {
                MaterialTheme.typography.labelLarge
            } else {
                MaterialTheme.typography.labelMedium.copy(
                    fontSize = 24.sp
                )
            }
        )
    }
}
