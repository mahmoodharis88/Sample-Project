package com.example.dummyproject.data.remote

import com.example.dummyproject.data.remote.network.NetworkApi
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import retrofit2.Response

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun getRepositories(): Response<RepositoriesResponse> {
        return networkApi.loadRepositories()
    }

}