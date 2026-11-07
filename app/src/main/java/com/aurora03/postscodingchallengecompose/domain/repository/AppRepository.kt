package com.aurora03.postscodingchallengecompose.domain.repository

import com.aurora03.postscodingchallengecompose.core.util.Resource
import com.aurora03.postscodingchallengecompose.domain.model.PostsPage
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    /**
     *Contrato donde se define las operaciones que la aplicacion necesita para obtener
     * las publicaciones
     */

    //Obtiene la primera pagina para mostrar los personajes al abrir la pantalla.
    fun getPosts(): Flow<Resource<PostsPage>>
}