package com.example.dummyproject.viewmodels

import MockTestUtil
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dummyproject.data.local.LocalDataSource
import com.example.dummyproject.data.local.database.RepositoriesDao
import com.example.dummyproject.data.remote.RemoteDataSource
import com.example.dummyproject.domain.repository.RepositoryImpl
import com.example.dummyproject.domain.usecases.LoadRepositoriesUsecase
import com.example.dummyproject.presentation.ui.ContentState
import com.example.dummyproject.presentation.ui.MainUiState
import com.example.dummyproject.presentation.ui.MainViewModel
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var repository: RepositoryImpl
    private lateinit var remote: RemoteDataSource
    private lateinit var local: LocalDataSource
    private lateinit var viewModel: MainViewModel
    private lateinit var repositoriesDao: RepositoriesDao

    @MockK
    private lateinit var loadRepositoriesUsecase: LoadRepositoriesUsecase


    @Before
    public override fun setUp() {

        MockKAnnotations.init(this)

        Dispatchers.setMain(testDispatcher)

        remote = mock(RemoteDataSource::class.java)
        local = mock(LocalDataSource::class.java)

        repositoriesDao = mock(RepositoriesDao::class.java)

        repository = RepositoryImpl(remoteDataSource = remote, localDataSource = local)

    }

    @After
    public override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
    }


    @Test
    fun getRepositoriesSuccessCase() = testDispatcher.runBlockingTest {
        val givenRepositories = MockTestUtil.createRepositories(3)

        val uiObserver =
            mockk<Observer<MainUiState>>(relaxed = true)

        //when
        coEvery { loadRepositoriesUsecase.invoke() }.returns(
            flowOf(
                NetworkResult.Success(
                    RepositoriesResponse(incompleteResults = false, items = givenRepositories)
                )
            )
        )

        viewModel = MainViewModel(loadRepositoriesUsecase)
        viewModel.uiStateLiveData.observeForever(uiObserver)

        coVerify(exactly = 1) { loadRepositoriesUsecase.invoke() }


        verify {
            uiObserver.onChanged(match {
                if (it is ContentState) {
                    it.response.items.isNotEmpty()
                } else {
                    false
                }
            })
        }


    }
}