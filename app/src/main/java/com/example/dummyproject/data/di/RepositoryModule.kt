package com.example.dummyproject.data.di

import com.example.dummyproject.data.local.LocalDataSource
import com.example.dummyproject.data.remote.RemoteDataSource
import com.example.dummyproject.domain.repository.Repository
import com.example.dummyproject.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): Repository {
        return RepositoryImpl(remoteDataSource, localDataSource)
    }
}
