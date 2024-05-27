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
package com.example.dummyproject.data.remote

import com.example.dummyproject.MainCoroutineRule
import com.example.dummyproject.data.remote.api.ApiAbstract
import com.example.dummyproject.data.remote.network.NetworkApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class NetworkApiTest : ApiAbstract<NetworkApi>() {

    private lateinit var apiService: NetworkApi

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        apiService = createService(NetworkApi::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test loadRepositories returns list of Repositories`() = runBlocking {
        // Given
        enqueueResponse("/repositories_response.json")

        // Invoke
        val response = apiService.loadRepositories()
        val responseBody = response.body()?.items!!
        mockWebServer.takeRequest()

        // Then
        assertThat(responseBody[0].name, `is`("go"))
        assertThat(responseBody[0].fullName, `is`("golang/go"))
        assertThat(responseBody[0].description, `is`("The Go programming language"))
        assertThat(responseBody[0].language, `is`("Go"))
    }

}
