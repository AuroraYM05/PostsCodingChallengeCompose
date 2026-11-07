package com.aurora03.postscodingchallengecompose.presentation.posts

import kotlin.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aurora03.postscodingchallengecompose.R
import com.aurora03.postscodingchallengecompose.domain.model.Post
import com.aurora03.postscodingchallengecompose.ui.theme.Inter
import com.aurora03.postscodingchallengecompose.ui.theme.PostsCodingChallengeComposeTheme


@Composable
fun PostListScreen(
    viewModel: PostsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PostListContent(
        state = uiState,
        onAction = { action ->
            when(action) {
                is PostListAction.NavigateBack -> onNavigateBack()
                else -> viewModel.onAction(action)
            }
        }
    )

}

//Sirve para el TopAppBar porque es una api experimental
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PostListContent(
    state: PostListState,
    onAction: (PostListAction) -> Unit
){
    //Permite mantener el estado de desplazamiento de LazyColumn
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        onAction(PostListAction.LoadPost)
    }

    //Esto hace que muestre el error en SnackBar cuando exista
    LaunchedEffect(state.hasError, state.errorMessage) {
        //Muestra el Snackbar solamente cuando:
        // 1. Ocurrió un error.
        // 2. Ya existen publicaciones cargadas.
        // 3. El mensaje de error no está vacío.
        val message = state.errorMessage
        if (state.hasError && !message.isNullOrBlank() && state.posts.isNotEmpty()){
            snackbarHostState.showSnackbar(message)
            onAction(PostListAction.ClearError)
        }
    }

    Scaffold(
        //Sirve para definir el contenido de la parte superior
        modifier = Modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {onAction(PostListAction.NavigateBack)}) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text(
                        text = stringResource(R.string.text_publications),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                )}
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            when {
                //Si la aplicacion esta cargando y todavia no tiene publicaciones.
                //Se muestra el indicador de carga
                state.isLoading && state.posts.isEmpty() -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                //Cuando hay publicaciones disponibles
                //Se muestra las publicaciones
                state.posts.isNotEmpty() -> {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = state.posts,
                            key = { post -> post.id}
                        ) { post ->
                            PostsListItem(
                                post = post
                            )
                        }
                    }
                }

                //Error en la carga y no hay publicaciones disponibles.
                //Se muestra el mensaje de error y un boton para recargar
                state.hasError && state.posts.isEmpty() -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.errorMessage ?: stringResource(R.string.text_error_message),
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = {
                            onAction(PostListAction.LoadPost)
                        }) { Text(stringResource(R.string.txt_btn_error))}
                    }
                    //Cuando se realizo la carga sin errores pero no hay publicaciones disponibles.
                    //Se muestra un mensaje donde que dice que no hay publicaciones
                } else ->{
                    Text(
                        text = stringResource(R.string.text_empty_posts),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

val posts = listOf(
    Post(
        id = 1,
        title = "His mother had always taught him",
        body = "His mother had always taught him not to ever think of himself as better than others. He'd tried to live by this motto. He never looked down on those who were less fortunate or who had less money than him. But the stupidity of the group of people he was talking to made him change his mind.",
        tags = listOf("history", "american", "crime"),
        likes = 192,
        dislikes = 25,
        views = 305,
        userId = 121
    ),
    Post(
        id = 2,
        title = "He was an expert but not in a discipline",
        body = "He was an expert but not in a discipline that anyone could fully appreciate. He knew how to hold the cone just right so that the soft server ice-cream fell into it at the precise angle to form a perfect cone each and every time. It had taken years to perfect and he could now do it without even putting any thought behind it.",
        tags = listOf("french", "fiction", "english"),
        likes = 859,
        dislikes = 32,
        views = 4884,
        userId = 91
    )
)

@Preview(showBackground = true)
@Composable
fun PostListScreenPreview() {
    PostsCodingChallengeComposeTheme() {
        PostListContent(
            state = PostListState(
                posts = posts
            ), onAction = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostListScreenDarkPreview() {
    PostsCodingChallengeComposeTheme(darkTheme = true) {
        PostListContent(
            state = PostListState(
                posts = posts
            ), onAction = {}
        )
    }
}