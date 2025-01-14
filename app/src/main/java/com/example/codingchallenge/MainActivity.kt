package com.example.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codingchallenge.ui.theme.CodingChallengeTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.codingchallenge.ui.AppNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodingChallengeTheme {
                Scaffold(

                    content = { paddingValues ->
                        Box(modifier = Modifier.padding(paddingValues)) {
                            AppNavigator()
                        }
                    }
                )
            }
        }
    }
}


