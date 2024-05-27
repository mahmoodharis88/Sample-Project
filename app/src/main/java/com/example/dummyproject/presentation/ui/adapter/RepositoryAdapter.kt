package com.example.dummyproject.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyproject.databinding.RepositoryItemDataBinding
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.ItemsDiffUtil

class RepositoryAdapter(
    private var repositories: List<RepositoriesResponse.Item> = listOf(),
    private val adapterOnClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RepositoryViewHolder>() {

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemDataBinding.inflate(layoutInflater, parent, false)
        return RepositoryViewHolder(binding, adapterOnClick)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position], position)

    }

    fun datasetChanged(repositories: List<RepositoriesResponse.Item>) {
//        this.repositories = repositories
//        notifyDataSetChanged()

        val itemsDiffUtil =
            ItemsDiffUtil(this.repositories, repositories)
        val diffUtilResult = DiffUtil.calculateDiff(itemsDiffUtil)
        this.repositories = ArrayList(repositories)
        diffUtilResult.dispatchUpdatesTo(this)

    }

    override fun getItemCount(): Int {
        return repositories.size

    }

}