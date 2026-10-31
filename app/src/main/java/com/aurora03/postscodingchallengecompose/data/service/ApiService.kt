package com.aurora03.postscodingchallengecompose.data.service

import com.aurora03.postscodingchallengecompose.data.model.PostResponseDto
import retrofit2.Response
import retrofit2.http.GET

/**
 * Aqui retrofit hace la peticion HTTP a la API
 */
interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<PostResponseDto>

}