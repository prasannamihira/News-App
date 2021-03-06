package au.com.swivelgroup.newsapp.ui.main.tabs.topnews

import android.content.SharedPreferences
import au.com.swivelgroup.newsapp.BuildConfig
import au.com.swivelgroup.newsapp.data.remote.TopNewsHeadlinesResponse
import au.com.swivelgroup.newsapp.repository.ApiService
import org.json.JSONObject
import javax.inject.Inject

class TopNewsHeadlinesVM @Inject constructor(
    private val sharedPref: SharedPreferences,
    private val apiService: ApiService
) {

    var errorMessage: String = ""
    var isSuccessResponse: Boolean = false
    private var apiKey: String = BuildConfig.API_KEY

    var dataResponse: TopNewsHeadlinesResponse? = null

    /**
     * get news headlines data by api key
     */
    fun getTopNewsHeadlinesData() =

        // news api call for get top news headlines using api key
        apiService.fetchTopNewsHeadlinesList(apiKey)
            .map {
                if (it.isSuccessful) {
                    dataResponse = it.body()
                    isSuccessResponse = true
                } else {
                    // error response
                    errorMessage = try {
                        val jObjError = JSONObject(it.errorBody()?.string())
                        var errorMessages = jObjError.getJSONObject("data")
                        errorMessages.getString("message")
                    } catch (e: Exception) {
                        e.message.toString()
                    }
                }
            }
}