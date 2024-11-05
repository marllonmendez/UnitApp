package com.marllonmendez.conversordeunidades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marllonmendez.conversordeunidades.ui.theme.unitConverterTheme
import com.marllonmendez.conversordeunidades.view.main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            unitConverterTheme {
                val controller = rememberNavController()
                NavHost(controller, startDestination = "main") {
                    composable("main") {
                        main(controller)
                    }
                }
            }
        }
    }
}
