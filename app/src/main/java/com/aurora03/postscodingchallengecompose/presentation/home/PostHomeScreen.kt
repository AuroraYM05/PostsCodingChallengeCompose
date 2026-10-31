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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Pantalla principal de la aplicacion que contiene un Texto como titulo
 * y un botón
 */


@Composable
fun PostHomeScreen(
    onNavigateToPosts: () -> Unit,
    viewModel: PostHomeViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PostHomeContent(
        state = uiState,
        onAction = { action ->
            when(action){
                PostHomeActions.OnPostClicked ->onNavigateToPosts()
            }
        }
    )
}
@Composable
fun PostHomeContent(
    state: PostHomeState,
    onAction : (PostHomeActions) -> Unit
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

            Button(onClick ={
                onAction(PostHomeActions.OnPostClicked)
            },
                colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
            ) {
                Text(
                    text = "Ver Posts",
                    color = Color.White
                )
            }

        }
    }
}

@Preview (showBackground = true, name = "Content")
@Composable
fun PostHomeScreenPreview(){
    PostHomeContent(
        state = PostHomeState(),
        onAction = {}
    )
}
