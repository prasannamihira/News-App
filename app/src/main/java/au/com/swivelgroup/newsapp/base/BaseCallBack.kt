package au.com.swivelgroup.newsapp.base

import au.com.swivelgroup.newsapp.data.model.Status

open class BaseCallBack<T> {
    companion object {
        const val SUCCESS = 200L
        const val BAD_REQUEST = 400L
        const val UNAUTHORIZED_ERROR = 401L
        const val TOKEN_EXPIRE_ERROR = 402L
        const val NOT_FOUND = 404L
        const val UNAUTHORIZED_EMAIL_NOT_IN = 461L
        const val UNAUTHORIZED_PASSWORD_ERROR = 462L
        const val UNKNOWN = 472L


        @Retention(AnnotationRetention.SOURCE)
//        @IntDef(SUCCESS, BAD_REQUEST, UNAUTHORIZED_ERROR, NOT_FOUND,UNAUTHORIZED_EMAIL_NOT_IN,UNAUTHORIZED_PASSWORD_ERROR,UNKNOWN)
        annotation class ServerCode
    }

    var status: Status? = null
}