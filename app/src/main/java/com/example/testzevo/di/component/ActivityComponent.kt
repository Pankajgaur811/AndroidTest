package com.example.testzevo.di.component

import com.example.testzevo.di.ActivityModule
import com.example.testzevo.di.ActivityScope
import com.example.testzevo.ui.view.home.HomeActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: HomeActivity)
}