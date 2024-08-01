package com.example.quote_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quote_ID:Int = 0,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String
    //val tag:List<String>
)