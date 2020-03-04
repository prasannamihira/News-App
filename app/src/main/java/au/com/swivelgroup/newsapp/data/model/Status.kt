package au.com.swivelgroup.newsapp.data.model

import au.com.swivelgroup.newsapp.base.BaseCallBack

data class Status(
    @BaseCallBack.Companion.ServerCode val code: Int, val status: String,
    val message: String
)