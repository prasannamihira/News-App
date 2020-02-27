package au.com.swivelgroup.newsapp.data.remote

import au.com.swivelgroup.newsapp.data.model.Status

abstract class StatusResponse: BaseResponse {

    abstract override fun status(): Status
}