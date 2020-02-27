package au.com.swivelgroup.newsapp.di

import android.preference.PreferenceManager
import au.com.swivelgroup.newsapp.BuildConfig
import au.com.swivelgroup.newsapp.app.App
import au.com.swivelgroup.newsapp.repository.ApiService
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApp() = App

    @Provides
    @Singleton
    fun provideSharedPreference() = PreferenceManager.getDefaultSharedPreferences(app)!!

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .addInterceptor(interceptor).build()
            )
            .build()
            .create(ApiService::class.java)
    }
}