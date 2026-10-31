package com.aurora03.postscodingchallengecompose.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    data object PostHome : Screen(){

    }

    @Serializable
    data object PostList : Screen(){

    }
}