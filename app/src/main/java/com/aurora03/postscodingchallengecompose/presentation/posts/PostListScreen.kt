package com.aurora03.postscodingchallengecompose.presentation.posts

import kotlin.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aurora03.postscodingchallengecompose.domain.model.Post
import com.aurora03.postscodingchallengecompose.ui.theme.PostsCodingChallengeComposeTheme


@Composable
fun PostListScreen(
    viewModel: PostsViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
       viewModel.onAction(PostListAction.LoadPost)
    }

    PostListContent(
        state = uiState,
        onAction = { action ->
            viewModel.onAction(action)
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

    Scaffold(
        //Sirve para definir el contenido de la parte superior
        topBar = {
            TopAppBar(
                title = {Text("Lista de publicaciones")}
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
                //Si la aplicacion esta cargando y no tiene publicaciones
                state.isLoading && state.posts.isEmpty() -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

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