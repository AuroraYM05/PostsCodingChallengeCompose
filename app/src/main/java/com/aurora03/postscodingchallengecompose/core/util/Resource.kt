package com.aurora03.postscodingchallengecompose.core.util

sealed class Resource <out T> {
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * Estado de error: la operación falló.
     * @param message Mensaje descriptivo del error para mostrar al usuario
     * @param throwable Excepción original para logs/debugging (opcional)
     */
    data class Error(val message: String, val throwable: Throwable? = null) : Resource<Nothing>()

    /**
     * Estado de carga: la operación está en progreso.
     * data object porque no tiene datos/estado interno
     */
    data object Loading : Resource<Nothing>()
}