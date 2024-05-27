package com.example.dummyproject.domain.repository

import com.example.dummyproject.data.local.LocalDataSource
import com.example.dummyproject.data.remote.RemoteDataSource
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) : Repository {

    val remote = remoteDataSource

    val local = localDataSource
    override suspend fun loadRepositories(): Flow<NetworkResult<RepositoriesResponse>> {
        return flow {
            try {
                remote.getRepositories().apply {
                    if (this.isSuccessful) {
                        local.insertRepositories(this.body()!!)
                    }
                    emit(NetworkResult.Success(data = local.readRepositories()))

                }

            } catch (e: Exception) {
                if (local.readRepositories() == null) {
                    emit(NetworkResult.Error(message = e.message.toString(), data = null))
                } else {
                    emit(NetworkResult.Success(data = local.readRepositories()))
                }
            }

        }
    }

}