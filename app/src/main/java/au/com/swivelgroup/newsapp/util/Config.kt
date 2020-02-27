package au.com.swivelgroup.newsapp.util

interface Config {
    companion object {

        const val CONTENT_TYPE_JSON = "Content-Type:application/json; charset=utf8"
        const val ACCESS_TOKEN_PREFIX = "Bearer"

        val DEVICE_TYPE = "android"
        const val AUTHORIZATION = "Authorization"
    }
}