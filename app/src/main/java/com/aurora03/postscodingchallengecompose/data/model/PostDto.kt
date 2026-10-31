package com.aurora03.postscodingchallengecompose.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DTO representa la respuesta general de la API
 */
@JsonClass(generateAdapter = true)
data class PostResponseDto(
    // Nombre del campo que contiene la lista de publicaciones en el JSON.
    @Json(name = "posts") val posts: List<PostDTO>
)

/**
 * DTO que representa una publicacion individual de cada publicacion
 */
@JsonClass(generateAdapter = true)
data class PostDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "body") val body: String,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "reactions") val reactions: ReactionsDTO,
    @Json(name = "views") val views: Int,
    @Json(name = "userId") val userId: Int
)

/**
 * DTO para las reacciones de la publicación como likes y dislikes
 */

@JsonClass(generateAdapter = true)
data class ReactionsDTO(
    @Json(name = "likes") val likes: Int,
    @Json(name = "dislikes") val dislikes: Int
)

