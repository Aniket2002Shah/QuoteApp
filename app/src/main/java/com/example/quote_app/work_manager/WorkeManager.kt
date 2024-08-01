package com.example.quote_app.work_manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.quote_app.QuoteApplication
import kotlinx.coroutines.*

class WorkeManager(private val context: Context,parameter:WorkerParameters) : Worker(context,parameter){
    @OptIn(DelicateCoroutinesApi::class)
    override fun doWork(): Result {
        Log.d("TAG","Shingekinokyojin")

        val repository = (applicationContext as QuoteApplication ).repository
        CoroutineScope(Dispatchers.IO).launch{
            repository.getRandomQuotes()
        }
        return Result.success()
    }

}