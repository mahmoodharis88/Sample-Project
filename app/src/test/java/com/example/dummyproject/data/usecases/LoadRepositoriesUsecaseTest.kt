/*
* Copyright 2021 Wajahat Karim (https://wajahatkarim.com)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dummyproject.data.usecases

import MockTestUtil
import com.example.dummyproject.domain.repository.Repository
import com.example.dummyproject.domain.usecases.LoadRepositoriesUsecase
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.NetworkResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoadRepositoriesUsecaseTest {

    @MockK
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking loadRepositoriesUsecase gives list of Repositories`() = runBlocking {
        // Given
        val usecase = LoadRepositoriesUsecase(repository)
        val givenRepositories = MockTestUtil.createRepositories(3)

        // When
        coEvery { repository.loadRepositories() }
            .returns(flowOf(NetworkResult.Success(RepositoriesResponse(false, givenRepositories))))

        // Invoke
        val repositoriesListFlow = usecase()

        // Then
        MatcherAssert.assertThat(repositoriesListFlow, CoreMatchers.notNullValue())

        val repositoriesListDataState = repositoriesListFlow.first()
        MatcherAssert.assertThat(repositoriesListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            repositoriesListDataState,
            CoreMatchers.instanceOf(NetworkResult.Success::class.java)
        )

        val repositoriesList = (repositoriesListDataState as NetworkResult.Success).data
        MatcherAssert.assertThat(repositoriesList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(repositoriesList?.items?.size, `is`(givenRepositories.size))
    }
}
