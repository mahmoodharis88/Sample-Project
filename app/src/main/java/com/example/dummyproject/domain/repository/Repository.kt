package com.example.dummyproject.domain.repository

import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadRepositories():  Flow<NetworkResult<RepositoriesResponse>>
}
