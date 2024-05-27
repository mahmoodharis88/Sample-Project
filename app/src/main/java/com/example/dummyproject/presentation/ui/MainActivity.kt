package com.example.dummyproject.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyproject.R
import com.example.dummyproject.databinding.ActivityMainBinding
import com.example.dummyproject.presentation.base.BaseActivity
import com.example.dummyproject.presentation.ui.adapter.RepositoryAdapter
import com.example.dummyproject.presentation.ui.model.RepositoriesResponse
import com.example.dummyproject.presentation.util.observer.gone
import com.example.dummyproject.presentation.util.observer.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var binding: ActivityMainBinding? = null
    private val mainViewModel: MainViewModel by viewModels()

    private var repositories: List<RepositoriesResponse.Item> = listOf()

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
        { position ->
            repositories[position].expand = !repositories[position].expand
            repositoryAdapter.notifyItemChanged(position)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        /**======================== setting Adapter ========================*/
        binding?.recyclerView?.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        val dividerItemDecoration = DividerItemDecoration(
            this,
            LinearLayoutManager.VERTICAL
        )

        binding?.recyclerView?.addItemDecoration(dividerItemDecoration)

        binding?.recyclerView?.adapter = repositoryAdapter


        binding?.errorView?.retryButton?.setOnClickListener {
            mainViewModel.getRepositories()
        }

        binding?.refreshLayout?.setOnRefreshListener {
            mainViewModel.getRepositories()
            binding?.refreshLayout?.isRefreshing = false
        }

        /**======================== OBSERVERS ========================*/

        mainViewModel.uiStateLiveData.observe(this) { state ->
            when (state) {
                is LoadingState -> {
                    binding?.refreshLayout?.gone()
                    binding?.errorView?.root?.gone()
                    binding?.shimmerView?.root?.visible()
                }

                is ContentState -> {
                    binding?.refreshLayout?.visible()
                    binding?.errorView?.root?.gone()
                    binding?.shimmerView?.root?.gone()

                    refreshAdapter(state.response.items)
                }

                is ErrorState -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                    binding?.refreshLayout?.gone()
                    binding?.errorView?.root?.visible()
                    binding?.shimmerView?.root?.gone()
                }
            }
        }

    }

    private fun refreshAdapter(items: List<RepositoriesResponse.Item>?) {
        items?.let {
            repositories = it
            repositoryAdapter.datasetChanged(repositories)
        }
    }

}