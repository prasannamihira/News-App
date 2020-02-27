package au.com.swivelgroup.newsapp.app

import android.app.Application
import au.com.swivelgroup.newsapp.data.remote.TopNewsHeadlinesResponse
import au.com.swivelgroup.newsapp.di.AppComponent
import au.com.swivelgroup.newsapp.di.AppModule
import au.com.swivelgroup.newsapp.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var instance: App

        // Applications modules which inject
        lateinit var appComponent: AppComponent

        var topNewsHeadlinesResponse: TopNewsHeadlinesResponse? = null
    }

    override fun onCreate() {
        super.onCreate()

        // News application instance
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)
    }
}