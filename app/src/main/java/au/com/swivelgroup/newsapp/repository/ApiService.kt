package au.com.swivelgroup.newsapp.repository

import au.com.swivelgroup.newsapp.data.remote.TopNewsHeadlinesResponse
import au.com.swivelgroup.newsapp.util.Config
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * News api services
 */
interface ApiService {

    /**
     * fetch news headlines list
     *
     * @param apiKey
     */
    @Headers(Config.CONTENT_TYPE_JSON)
    @GET("top-headlines?country=us")
    fun fetchTopNewsHeadlinesList(@Query("apiKey") apiKey: String): Flowable<Response<TopNewsHeadlinesResponse>>

    /**
     * fetch preference news headlines list
     *
     * @param apiKey
     * @param query 
     */
    @Headers(Config.CONTENT_TYPE_JSON)
    @GET("everything?sortBy=popularity")
    fun fetchPreferenceNewsList(@Query("q") query: String, @Query("apiKey") apiKey: String): Flowable<Response<TopNewsHeadlinesResponse>>

}