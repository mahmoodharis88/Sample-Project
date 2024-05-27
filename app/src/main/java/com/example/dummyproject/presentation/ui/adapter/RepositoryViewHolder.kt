package com.example.dummyproject.presentation.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.RepositoryItemDataBinding
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse

class RepositoryViewHolder(
    val binding: RepositoryItemDataBinding?,
    private val adapterOnClick: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding?.root!!) {


    fun bind(model: RepositoriesResponse.Item, position:Int) {
        binding?.model = model
        binding?.executePendingBindings()

        binding?.root?.setOnClickListener{
            adapterOnClick(position)
        }

    }
}