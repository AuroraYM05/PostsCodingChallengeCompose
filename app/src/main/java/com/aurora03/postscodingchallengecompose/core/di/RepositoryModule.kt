package com.aurora03.postscodingchallengecompose.core.di

import com.aurora03.postscodingchallengecompose.data.repository.AppRepositoryImpl
import com.aurora03.postscodingchallengecompose.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Permite que, cuando una clase solicite AppRepository, Hilt le proporcione
 * una instancia de AppRepositoryImpl.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAppRepository(
        appRepositoryImp: AppRepositoryImpl
    ): AppRepository
}