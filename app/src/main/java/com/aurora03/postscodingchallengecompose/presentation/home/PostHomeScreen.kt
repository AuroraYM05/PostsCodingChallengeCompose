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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aurora03.postscodingchallengecompose.R
import com.aurora03.postscodingchallengecompose.ui.theme.Inter
import com.aurora03.postscodingchallengecompose.ui.theme.PostsCodingChallengeComposeTheme

/**
 * Pantalla principal de la aplicacion que contiene un Texto como titulo
 * y un botón
 */
@Composable
fun PostHomeScreen(
    onNavigateToPosts: () -> Unit,
){

    PostHomeContent(
        onNavigateToPosts = onNavigateToPosts
    )
}
@Composable
fun PostHomeContent(
    onNavigateToPosts: () -> Unit
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.secondaryContainer
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
                text = stringResource(id = R.string.app_title),
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onNavigateToPosts,
                colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
            ) {
                Text(
                    text = stringResource(id = R.string.name_btn),
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.background

                )
            }

        }
    }
}

@Preview (showBackground = true, name = "Content")
@Composable
fun PostHomeScreenPreview(){
    PostsCodingChallengeComposeTheme{
        PostHomeContent(
            onNavigateToPosts = {}
        )
    }
}

@Preview (showBackground = true, name = "Content")
@Composable
fun PostHomeScreenDarkPreview(){
    PostsCodingChallengeComposeTheme(darkTheme = true){
        PostHomeContent(
            onNavigateToPosts = {}
        )
    }
}
