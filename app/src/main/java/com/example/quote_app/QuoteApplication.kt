package com.example.quote_app

import android.annotation.SuppressLint
import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.quote_app.database.QuotesDatabase
import com.example.quote_app.repository.QuoteRepository
import com.example.quote_app.web_services.QuotesServices
import com.example.quote_app.web_services.RetrofitHelper
import com.example.quote_app.work_manager.WorkeManager
import java.util.concurrent.TimeUnit

class QuoteApplication: Application() {

    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initalize()
        workManager()
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun workManager(){
     val constraintBuilder = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workrequest = PeriodicWorkRequest.Builder(WorkeManager::class.java,2,TimeUnit.MINUTES)
            .setConstraints(constraintBuilder)
            .build()
        WorkManager.getInstance(this).enqueue(workrequest)
    }
    private fun initalize() {
        val quoteService = RetrofitHelper.getInstance().create(QuotesServices::class.java)
        val quoteDao = QuotesDatabase.getDatabase(applicationContext).resultDao()
        repository = QuoteRepository(quoteService,quoteDao,applicationContext)
    }

}