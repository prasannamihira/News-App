package au.com.swivelgroup.newsapp.base

import androidx.annotation.IntDef
import au.com.swivelgroup.newsapp.data.model.Status

open class BaseCallBack<T> {
    companion object {
        const val SUCCESS = 200
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED_ERROR = 401
        const val TOKEN_EXPIRE_ERROR = 402
        const val NOT_FOUND = 404
        const val UNKNOWN = 472


        @Retention(AnnotationRetention.SOURCE)
        @IntDef(SUCCESS, BAD_REQUEST, UNAUTHORIZED_ERROR, TOKEN_EXPIRE_ERROR, NOT_FOUND, UNKNOWN)
        annotation class ServerCode
    }

    var status: Status? = null
}