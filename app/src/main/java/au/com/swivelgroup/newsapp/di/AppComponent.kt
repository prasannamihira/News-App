package au.com.swivelgroup.newsapp.di

import au.com.swivelgroup.newsapp.app.App
import au.com.swivelgroup.newsapp.ui.main.tabs.topnews.TopNewsHeadlinesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject(topNewsHeadlinesFragment: TopNewsHeadlinesFragment)
}