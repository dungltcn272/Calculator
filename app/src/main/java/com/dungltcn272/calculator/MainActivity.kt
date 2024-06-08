package com.dungltcn272.calculator

import com.dungltcn272.calculator.ui.theme.Background
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import com.dungltcn272.calculator.caculator.CalculatorScreen
import com.dungltcn272.calculator.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                CalculatorScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .background(Background)
                )
            }
        }
    }
}


