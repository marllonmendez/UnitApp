package com.marllonmendez.conversordeunidades.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.marllonmendez.conversordeunidades.ui.theme.PrimaryDark
import com.marllonmendez.conversordeunidades.ui.theme.PrimaryLight
import com.marllonmendez.conversordeunidades.ui.theme.SurfaceLight
import com.marllonmendez.conversordeunidades.ui.theme.Tertiary

@Composable
fun numericKeyboard(
    onNumber: (String) -> Unit,
    onComma: () -> Unit,
    onClear: () -> Unit,
    onBackspace: () -> Unit,
    onExchange: () -> Unit,
    onEquals: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        customRow {
            (7..9).forEach { number ->
                numberButton(
                    number = number.toString(),
                    onClick = onNumber
                )
            }

            actionButton(
                icon = Icons.Filled.Delete,
                onClick = onClear
            )
        }

        customRow {
            (4..6).forEach { number ->
                numberButton(
                    number = number.toString(),
                    onClick = onNumber
                )
            }

            actionButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = onBackspace
            )
        }

        customRow {
            (1..3).forEach { number ->
                numberButton(
                    number = number.toString(),
                    onClick = onNumber
                )
            }

            actionButton(
                label = ",",
                onClick = onComma
            )
        }

        customRow {
            actionButton(
                icon = Icons.Filled.ChangeCircle,
                onClick = onExchange
            )

            numberButton(
                number = "0",
                onClick = onNumber
            )

            actionButton(
                icon = Icons.Filled.ChangeCircle,
                onClick = onExchange
            )

            actionButton(
                icon = Icons.Filled.Calculate,
                onClick = onEquals
            )
        }
    }
}

@Composable
private fun numberButton(number: String, onClick: (String) -> Unit) {
    Button(
        onClick = { onClick(number) },
        modifier = Modifier
            .size(70.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryDark
        )
    ) {
        Text(
            text = number,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 27.sp
            ),
        )
    }
}

@Composable
private fun actionButton(
    icon: ImageVector? = null,
    label: String? = null,
    onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(70.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = when (icon) {
                Icons.Filled.Calculate -> PrimaryLight
                Icons.Filled.Delete -> Tertiary
                else -> MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SurfaceLight
            )
        } else if (label != null) {
            Text(
                label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 24.sp
                )
            )
        }
    }
}

@Composable
private fun customRow(
    modifier: Modifier = Modifier,
    spaceBetween: Arrangement.Horizontal = Arrangement.SpaceBetween,
    children: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = spaceBetween
    ) {
        children()
    }
}
