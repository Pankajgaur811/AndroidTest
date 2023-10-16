package com.example.testzevo.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testzevo.data.repositories.TopNewsHeadLinesRepository
import com.example.testzevo.ui.base.ViewModelProviderFactory
import com.example.testzevo.ui.view.home.TopNewsHeadLineAdapter
import com.example.testzevo.ui.view.home.viewmodel.TopNewsHeadlinesViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(topHeadlineRepository: TopNewsHeadLinesRepository): TopNewsHeadlinesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopNewsHeadlinesViewModel::class) {
                TopNewsHeadlinesViewModel(topHeadlineRepository)
            })[TopNewsHeadlinesViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopNewsHeadLineAdapter(ArrayList())

}