package au.com.swivelgroup.newsapp.data.local

import android.content.SharedPreferences

fun SharedPreferences.save(key: String, value: String?) = edit().putString(key, value).apply()