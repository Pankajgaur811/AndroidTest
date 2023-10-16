package com.example.testzevo

import android.app.Application
import com.example.testzevo.di.ApplicationModule
import com.example.testzevo.di.component.ApplicationComponent
import com.example.testzevo.di.component.DaggerApplicationComponent

class NewsApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){

            applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
            applicationComponent.inject(this)
    }

}