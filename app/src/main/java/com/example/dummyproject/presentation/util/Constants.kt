package com.example.dummyproject.presentation.util

class Constants {

    companion object {

        const val BASE_URL = "https://api.github.com"
        const val REPOSITORIES_API = "/search/repositories?q=language=+sort:stars"

        // ROOM Database
        const val DATABASE_NAME = "repositories_database"
    }

}