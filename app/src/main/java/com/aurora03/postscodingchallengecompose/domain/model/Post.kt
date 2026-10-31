package com.aurora03.postscodingchallengecompose.domain.model

//Modelo del correo que se usara para la aplicación
data class Post (
    val id: Int,
    val title: String,
    val body: String,
    val tags: List<String>,
    val likes: Int,
    val dislikes: Int,
    val views: Int,
    val userId: Int
)

data class PostsPage(
    val posts : List<Post>
)
