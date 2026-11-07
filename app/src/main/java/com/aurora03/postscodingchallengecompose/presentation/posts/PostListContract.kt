package com.aurora03.postscodingchallengecompose.presentation.posts

import com.aurora03.postscodingchallengecompose.domain.model.Post

data class PostListState(
    val posts : List<Post> = emptyList(),
    val isLoading : Boolean = false,
    val errorMessage: String? = null,
    val hasError: Boolean = false
)

sealed interface PostListAction {

    data object LoadPost : PostListAction

    data object ClearError : PostListAction

    data object NavigateBack: PostListAction



}