package com.example.dummyproject.data.remote.network

import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET(Constants.REPOSITORIES_API)
    suspend fun loadRepositories(): Response<RepositoriesResponse>

}