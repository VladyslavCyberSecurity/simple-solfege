package com.example.simplesolfege

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.simplesolfege.ui.MainScreen
import com.example.simplesolfege.ui.SplashScreen
import com.example.simplesolfege.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {

                var showSplash by remember { mutableStateOf(true) }

                if (showSplash)
                    SplashScreen { showSplash = false }
                else
                    MainScreen()
            }
        }
    }
}
