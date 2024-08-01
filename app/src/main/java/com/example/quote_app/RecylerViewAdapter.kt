package com.example.quote_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quote_app.databinding.ItemActivityBinding

class RecylerViewAdapter:ListAdapter<com.example.quote_app.database.Result,RecylerViewAdapter.RecyclerViewHolder>(DiffUtil()) {

    class DiffUtil:androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.quote_app.database.Result>(){
        override fun areItemsTheSame(
            oldItem: com.example.quote_app.database.Result,
            newItem: com.example.quote_app.database.Result
        ): Boolean {
           return oldItem._id==newItem._id
        }

        override fun areContentsTheSame(
            oldItem: com.example.quote_app.database.Result,
            newItem: com.example.quote_app.database.Result
        ): Boolean {
            return oldItem==newItem
        }

    }
    class RecyclerViewHolder(private val binding: ItemActivityBinding, itemView: View): RecyclerView.ViewHolder(itemView) {
        fun insert(item:com.example.quote_app.database.Result){
            binding.name.text=item.content
            binding.description.text= item.author
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<ItemActivityBinding>(inflater,R.layout.item_activity,parent,false)
        return RecyclerViewHolder(binding,binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
       val item = getItem(position)
        holder.insert(item)
    }
}