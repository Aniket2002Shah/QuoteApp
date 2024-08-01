package com.example.quote_app.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quote_app.repository.QuoteRepository

class MainViewModelFactory(private val quoteRepository: QuoteRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  MainViewModel(quoteRepository) as T
    }
}