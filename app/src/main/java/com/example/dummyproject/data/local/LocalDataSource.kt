package com.example.dummyproject.data.local

import com.example.dummyproject.data.local.database.RepositoriesDao
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val repositoriesDao: RepositoriesDao
) {

    fun readRepositories(): RepositoriesResponse {
        return repositoriesDao.readRepositories()
    }

    suspend fun insertRepositories(repositoriesResponse: RepositoriesResponse) {
        repositoriesDao.insertRepositories(repositoriesResponse)
    }
}
