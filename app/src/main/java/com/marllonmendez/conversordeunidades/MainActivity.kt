package com.marllonmendez.conversordeunidades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.marllonmendez.conversordeunidades.ui.theme.unitConverterTheme
import com.marllonmendez.conversordeunidades.view.unitConverterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            unitConverterTheme {
                unitConverterScreen()
            }
        }
    }
}
