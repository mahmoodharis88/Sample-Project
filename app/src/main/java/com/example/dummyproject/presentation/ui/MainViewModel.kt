package com.example.dummyproject.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dummyproject.domain.usecases.LoadRepositoriesUsecase
import com.example.dummyproject.presentation.base.BaseViewModel
import com.example.dummyproject.presentation.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loadRepositoriesUsecase: LoadRepositoriesUsecase) :
    BaseViewModel() {

    private var _uiState = MutableLiveData<MainUiState>()
    var uiStateLiveData: LiveData<MainUiState> = _uiState


    init {
        getRepositories()
    }

    /**======== API call Methods ========*/

    /**======== get Repositories API call Methods ========*/
    fun getRepositories() = viewModelScope.launch(Dispatchers.IO) {

        loadRepositoriesUsecase().collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    _uiState.postValue(ContentState(response.data!!))
                }

                is NetworkResult.Error -> {
                    _uiState.postValue(response.message?.let { ErrorState(it) })
                }

                is NetworkResult.Loading -> {
                    _uiState.postValue(LoadingState)
                }
            }
        }
    }

}

