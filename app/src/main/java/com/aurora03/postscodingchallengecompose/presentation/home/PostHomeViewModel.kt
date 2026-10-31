package com.aurora03.postscodingchallengecompose.presentation.home

import androidx.lifecycle.ViewModel
import com.aurora03.postscodingchallengecompose.domain.usecase.GetPostsUseCase
import com.aurora03.postscodingchallengecompose.presentation.posts.PostListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PostHomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(PostHomeState())

    val uiState: StateFlow<PostHomeState> = _uiState.asStateFlow()

    fun onAction(action: PostHomeActions){
        when(action){
            is PostHomeActions.OnPostClicked -> {

            }

        }
    }

}