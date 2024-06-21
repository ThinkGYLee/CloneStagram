package com.gyleedev.clonestagram.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gyleedev.clonestagram.ui.theme.CloneStagramTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloneStagramTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CloneStagramScreen(
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}