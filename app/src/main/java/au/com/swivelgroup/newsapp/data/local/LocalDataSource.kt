package au.com.swivelgroup.newsapp.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class LocalDataSource {

    private var mSp: SharedPreferences

    @Inject
    constructor(context: Context) {
        mSp = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getSp(): SharedPreferences {
        return mSp
    }
}