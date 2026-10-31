package com.aurora03.postscodingchallengecompose.data.mapper

import com.aurora03.postscodingchallengecompose.data.model.PostDTO
import com.aurora03.postscodingchallengecompose.data.model.PostResponseDto
import com.aurora03.postscodingchallengecompose.domain.model.Post
import com.aurora03.postscodingchallengecompose.domain.model.PostsPage
import javax.inject.Inject

class PostMapper @Inject constructor() {
    fun mapToDomain(dto: PostDTO): Post{
        return Post(
            id = dto.id,
            title = dto.title,
            body = dto.body,
            tags = dto.tags,
            likes = dto.reactions.likes,
            dislikes = dto.reactions.dislikes,
            views = dto.views,
            userId = dto.userId
        )
    }

    /**
     * Convertir la respuesta de la API en una lista de posts del domain.
     */

    fun mapToPage(dto: PostResponseDto): PostsPage{
        return PostsPage(
            posts = dto.posts.map { postDTO ->
                mapToDomain(postDTO)
            }
        )
    }
}