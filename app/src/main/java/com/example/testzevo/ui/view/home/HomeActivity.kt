package com.example.testzevo.ui.view.home

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testzevo.NewsApplication
import com.example.testzevo.R
import com.example.testzevo.data.models.Article
import com.example.testzevo.di.ActivityModule
import com.example.testzevo.di.component.DaggerActivityComponent
import com.example.testzevo.ui.base.UiStateManager
import com.example.testzevo.ui.view.home.viewmodel.TopNewsHeadlinesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var topHeadlineViewModel: TopNewsHeadlinesViewModel

    @Inject
    lateinit var topNewsHeadLineAdapter: TopNewsHeadLineAdapter

    private lateinit var topNewsHeadlineRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupUI()

        setupObserver()
    }

    private fun setupUI() {
        topNewsHeadlineRecyclerView = findViewById(R.id.topNewsHeadLineRV)
        topNewsHeadlineRecyclerView.layoutManager = LinearLayoutManager(this)
        topNewsHeadlineRecyclerView.adapter = topNewsHeadLineAdapter
        progressBar = findViewById(R.id.progressBar)

    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.uiState.collect {
                    when (it) {
                        is UiStateManager.Success -> {
                            progressBar.visibility = View.GONE
                            renderList(it.data)
                            topNewsHeadlineRecyclerView.visibility = View.VISIBLE
                        }

                        is UiStateManager.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            topNewsHeadlineRecyclerView.visibility = View.GONE
                        }

                        is UiStateManager.Error -> {
                            //Handle Error
                            progressBar.visibility = View.GONE

                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        topNewsHeadLineAdapter.addData(articleList)
        topNewsHeadLineAdapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

}