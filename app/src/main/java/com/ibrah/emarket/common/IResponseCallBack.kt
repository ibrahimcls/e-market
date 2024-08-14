package com.ibrah.emarket.common

interface IResponseCallBack<T> {
    fun success(result:T)
    fun failure(message:String)
}