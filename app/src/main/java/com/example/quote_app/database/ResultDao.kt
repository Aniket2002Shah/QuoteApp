package com.example.quote_app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao {
    @Insert
    suspend fun addQuotes(result: List<Result>)

    @Query("delete from quote_table")
    suspend fun deleteQuotes()

    @Query("select * from quote_table")
    suspend fun getQuotes():List<Result>
}