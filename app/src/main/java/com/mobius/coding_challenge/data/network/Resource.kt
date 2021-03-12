package com.mobius.coding_challenge.data.network

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val message: String?,
        val errorBody: ResponseBody?,
        val throwable: Throwable?
    ) : Resource<Nothing>()

    fun checkResponse(onSuccess: (Success<T>) -> Unit, onError: (Failure) -> Unit) {
        when (this) {
            is Success -> onSuccess(this)
            is Failure -> onError(this)
        }
    }
}