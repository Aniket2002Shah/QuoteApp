package com.example.quote_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quote_app.databinding.ActivityMainBinding
import com.example.quote_app.model.QuoteList
import com.example.quote_app.response_handling.Response
import com.example.quote_app.view_model.MainViewModel
import com.example.quote_app.view_model.MainViewModelFactory
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecylerViewAdapter()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter= adapter


        val repository = (application as QuoteApplication).repository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.quoteLiveData.observe(this) {
            when(it) {

                is Response.Processing->{
                    binding.progressBar.visibility = VISIBLE

                }
                is Response.Success-> {
                    it?.let {
                        binding.progressBar.visibility = GONE

                        val quote = it.r_data?.results
                        adapter.submitList(quote)
                    }
                }
                is Response.Error->{
                    it?.let {
                        Toast.makeText(this,it.r_message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}
