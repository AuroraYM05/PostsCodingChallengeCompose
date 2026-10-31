package com.aurora03.postscodingchallengecompose.presentation.home

import com.aurora03.postscodingchallengecompose.domain.model.Post


/**
 * Se declaran los estados de la UI para la pantalla home
 */
data class PostHomeState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hasError: Boolean = false
)

/**
 * Definimos las acciones que pueden ocurrir en la pantalla
 */
sealed interface PostHomeActions{

    data object OnPostClicked : PostHomeActions
}