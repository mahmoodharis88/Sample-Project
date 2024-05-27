package com.example.dummyproject.presentation.ui

import com.example.dummyproject.presentation.ui.model.RepositoriesResponse

sealed class MainUiState

object LoadingState : MainUiState()
class ContentState(val response: RepositoriesResponse) : MainUiState()
class ErrorState(val message: String) : MainUiState()