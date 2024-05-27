package com.example.dummyproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse

@Database(
    entities = [
        RepositoriesResponse::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RepositoryTypeConverter::class)
abstract class RepositoriesDatabase : RoomDatabase() {

    abstract fun repositoriesDao(): RepositoriesDao
}