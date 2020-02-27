package au.com.swivelgroup.newsapp.data.local

import android.content.SharedPreferences

class SharedPref {
    companion object {
        const val ACCESS_TOKEN = "accessToken"
    }
}

fun SharedPreferences.isAuthenticated() = !getString(SharedPref.ACCESS_TOKEN, "")!!.isEmpty()

fun SharedPreferences.save(key: String, value: String?) = edit().putString(key, value).apply()