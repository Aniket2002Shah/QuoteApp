package com.example.quote_app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quote_app.database.ResultDao
import com.example.quote_app.model.QuoteList
import com.example.quote_app.response_handling.Response
import com.example.quote_app.util.NetworkConnection
import com.example.quote_app.web_services.QuotesServices

class QuoteRepository(private val quoteService:QuotesServices,private val quotesDao:ResultDao,private val application: Context) {
    private var quoteMutableLiveData= MutableLiveData<Response<QuoteList>>()

    val quoteLiveData:LiveData<Response<QuoteList>>
    get()= quoteMutableLiveData

    suspend fun getQuotes(page:Int){

        if(NetworkConnection.isInternetAvailable(application)) {
            try {
                val result = quoteService.getQuotes(page)
                quoteMutableLiveData.postValue(Response.Processing())
                if (result?.body() != null) {
                    quotesDao.deleteQuotes()
                    quotesDao.addQuotes(result.body()!!.results)
                    quoteMutableLiveData.postValue(Response.Success(result.body()))
                }
                else{
                    quoteMutableLiveData.postValue(Response.Error("API not respond"))
                }
            }
            catch (e:Exception){
                quoteMutableLiveData.postValue(Response.Error(e.message))
            }
        }
        else{
            try {
            val quote=quotesDao.getQuotes()
            val quotelist = QuoteList(0,0,1,quote,0,0)
            quoteMutableLiveData.postValue(Response.Success(quotelist))

        }
            catch (e:Exception){
                quoteMutableLiveData.postValue(Response.Error(e.message))
            }
        }
    }

    suspend fun getRandomQuotes(){
       val value= (Math.random()*10).toInt()
        getQuotes(value)

    }
}