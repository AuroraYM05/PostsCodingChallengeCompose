package com.aurora03.postscodingchallengecompose.core.di

import com.aurora03.postscodingchallengecompose.data.service.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dummyjson.com/"

    /**
     * Provee el interceptor de logging para ver requests/responses en Logcat.
     * Level.BODY muestra headers, body completo - útil para desarrollo/debugging.
     * En producción normalmente se usaría Level.NONE.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provee el cliente OkHttp con interceptores configurados.
     * @param loggingInterceptor Interceptor para logs de red
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provee la instancia de Moshi para parseo JSON.
     * KotlinJsonAdapterFactory permite usar data classes de Kotlin sin anotaciones extras
     * (aunque aquí usamos @Json y @JsonClass explícitamente).
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    /**
     * Provee la instancia de Retrofit configurada con:
     * - baseUrl: URL base de la API
     * - client: OkHttpClient personalizado
     * - converterFactory: MoshiConverterFactory para parseo JSON
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * Crea e implementa la interfaz ApiService usando Retrofit.
     * Retrofit genera dinámicamente la implementación basándose en anotaciones (@GET, @Path, etc.)
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

