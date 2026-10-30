package com.aurora03.postscodingchallengecompose.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Pantalla principal de la aplicacion que contiene un Texto como titulo
 * y un botón
 */
@Composable
private fun PostHomeScreen(
    onNavigateToPosts: () -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = "Coding Challenge",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onNavigateToPosts,
                colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
            ) {
                Text(
                    text = "Ver Posts"
                )
            }

        }
    }
}

@Preview (showBackground = true, name = "Content")
@Composable
fun PostHomeScreenPreview(){
    PostHomeScreen(onNavigateToPosts = {})
}
