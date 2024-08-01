package com.example.quote_app.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quote_app.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository):ViewModel() {
 init {
     viewModelScope.launch(Dispatchers.IO){
         quoteRepository.getQuotes(1)
     }
 }
    val quoteLiveData
    get() = quoteRepository.quoteLiveData

}