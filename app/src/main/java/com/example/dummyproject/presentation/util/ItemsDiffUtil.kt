package com.example.dummyproject.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse

class ItemsDiffUtil(
    private val oldList: List<RepositoriesResponse.Item> ,
    private val newList: List<RepositoriesResponse.Item>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}