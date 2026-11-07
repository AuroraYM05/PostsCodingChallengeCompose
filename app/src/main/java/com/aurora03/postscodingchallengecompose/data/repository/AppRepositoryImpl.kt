package com.aurora03.postscodingchallengecompose.data.repository

import android.util.Log
import com.aurora03.postscodingchallengecompose.core.util.Resource
import com.aurora03.postscodingchallengecompose.data.mapper.PostMapper
import com.aurora03.postscodingchallengecompose.data.service.ApiService
import com.aurora03.postscodingchallengecompose.domain.model.PostsPage
import com.aurora03.postscodingchallengecompose.domain.repository.AppRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: PostMapper
) : AppRepository{

    companion object {
        private const val TAG = "AppRepositoryImpl"
    }

    override fun getPosts(): Flow<Resource<PostsPage>> = flow{

        //Indica que comienza la operación
        emit (Resource.Loading)

        try {
            //Registra el inicio de la aplicación en el Log
            Log.d(TAG, "Iniciando petición.")

            //Ejecuta la peticion HTTP mediante Retrofit
            //response guarda el resultado de esa petición.
            val response = apiService.getPosts()
            //response.body() =  Obtiene el cuerpo de la respuesta HTTP, los datos que se recibio.
            val dto = response.body()
            //response.isSucessful indica si el codigo HTTP indica exito.
            if (response.isSuccessful && dto != null){
                //Convierte el DTO recibido de la API en un modelo de dominio PostPage
                val page = mapper.mapToPage(dto)
                //Emite el resultado exitoso con los posts transformados.
                emit(Resource.Success(page))
            } else {
                emit(Resource.Error(response.message() ?: "No se logro cargar las publicaciones"))
            }


        }catch (e: CancellationException){
            throw e
        }
        catch (e: Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "Error desconocido", e))
        }
    }
}