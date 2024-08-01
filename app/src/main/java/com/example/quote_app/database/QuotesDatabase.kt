package com.example.quote_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class QuotesDatabase: RoomDatabase() {

    abstract fun resultDao(): ResultDao

    companion object {

        @Volatile
        private var INSTANCE: QuotesDatabase? = null

        fun getDatabase(context: Context): QuotesDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuotesDatabase::class.java,
                    "quotes_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}