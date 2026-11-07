package com.aurora03.postscodingchallengecompose.domain.usecase

import com.aurora03.postscodingchallengecompose.core.util.Resource
import com.aurora03.postscodingchallengecompose.domain.model.PostsPage
import com.aurora03.postscodingchallengecompose.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(

    //Recibe el repositorio que sabe como obtener los posts.
    private val repository: AppRepository
) {
    /**
     * Define una funcion que puede suspenderse y que se
     * puede llamra usando el nombre del objeto.
     */
    operator fun invoke(): Flow<Resource<PostsPage>> = repository.getPosts()

}