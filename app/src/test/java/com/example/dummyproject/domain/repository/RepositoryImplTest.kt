package com.example.dummyproject.domain.repository
import MockTestUtil
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryImplTest : TestCase() {

    @MockK
    private lateinit var repository: RepositoryImpl

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    public override fun setUp() {

        MockKAnnotations.init(this)

        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun test_success() = runTest {

        val givenRepositories = MockTestUtil.createRepositories(3)

        // When
        coEvery { repository.loadRepositories() }
            .returns(flowOf(NetworkResult.Success(RepositoriesResponse(false, givenRepositories))))

        val response = repository.loadRepositories().first().data?.items.orEmpty()

        assertThat(response[0].name, `is`("go"))
        assertThat(response[0].fullName, `is`("golang/go"))
        assertThat(response[0].description, `is`("The Go programming language"))
        assertThat(response[0].language, `is`("Go"))

    }
}