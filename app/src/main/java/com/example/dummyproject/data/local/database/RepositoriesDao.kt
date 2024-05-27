package com.example.dummyproject.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse


@Dao
interface RepositoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositoriesResponse: RepositoriesResponse)

    @Query("SELECT * FROM RepositoriesResponse")
    fun readRepositories(): RepositoriesResponse


}