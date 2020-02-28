package au.com.swivelgroup.newsapp.di

import au.com.swivelgroup.newsapp.app.App
import au.com.swivelgroup.newsapp.ui.main.tabs.topnews.TopNewsHeadlinesFragment
import au.com.swivelgroup.newsapp.ui.main.tabs.userpref.UserPreferenceNewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)

    fun inject(fragment: TopNewsHeadlinesFragment)

    fun inject(fragment: UserPreferenceNewsFragment)
}