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

import com.example.dummyproject.presentation.ui.model.RepositoriesResponse

class MockTestUtil {
    companion object {
        fun createRepositories(count: Int): List<RepositoriesResponse.Item> {
            return (0 until count).map {
                RepositoriesResponse.Item(
                    id = it,
                    name = "go",
                    fullName = "golang/go",
                    description = "The Go programming language",
                    forksCount = 17260,
                    language = "Go"
                )
            }
        }
    }
}
