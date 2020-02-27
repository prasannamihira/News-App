package au.com.swivelgroup.newsapp.data.remote

import au.com.swivelgroup.newsapp.data.model.Status

interface BaseResponse {

    fun status(): Status
}