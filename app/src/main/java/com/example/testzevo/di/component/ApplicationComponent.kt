package com.example.testzevo.di.component

import android.content.Context
import com.example.testzevo.NewsApplication
import com.example.testzevo.data.api.ApiServices
import com.example.testzevo.data.repositories.TopNewsHeadLinesRepository
import com.example.testzevo.di.ApplicationContext
import com.example.testzevo.di.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getApiService(): ApiServices

    fun getTopNewsHeadlineRepository(): TopNewsHeadLinesRepository

}