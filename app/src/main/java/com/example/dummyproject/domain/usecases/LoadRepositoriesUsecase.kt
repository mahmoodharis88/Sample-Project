package com.example.dummyproject.domain.usecases

import com.example.dummyproject.domain.repository.Repository
import javax.inject.Inject

class LoadRepositoriesUsecase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.loadRepositories()
}