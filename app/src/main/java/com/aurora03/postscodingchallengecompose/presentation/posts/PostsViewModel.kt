package com.aurora03.postscodingchallengecompose.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora03.postscodingchallengecompose.core.util.Resource
import com.aurora03.postscodingchallengecompose.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(PostListState())
    val uiState: StateFlow<PostListState> = _uiState.asStateFlow()

    fun onAction(action: PostListAction){
        when(action){
            is PostListAction.LoadPost -> loadPost()
            is PostListAction.ClearError -> clearError()
            is PostListAction.NavigateBack -> {
            }
        }
    }

    private fun loadPost(){
        viewModelScope.launch {
            getPostsUseCase().collect { resource ->
                when(resource) {
                    is Resource.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = currentState.posts.isEmpty(),
                                hasError = false,
                                errorMessage = null
                            )
                        }
                    }
                    is Resource.Success-> {
                        val newPosts = resource.data.posts

                        _uiState.update { currentState ->
                            currentState.copy(
                                posts = newPosts,
                                isLoading = false,
                                hasError = false,
                                errorMessage = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                hasError = true,
                                errorMessage = resource.message
                            )
                        }
                    }
                }
            }
        }
    }
    private fun clearError(){
        _uiState.update { currentState ->
            currentState.copy(
                hasError = false,
                errorMessage = null
            )
        }

    }

    private fun BackClick(){

    }
}
