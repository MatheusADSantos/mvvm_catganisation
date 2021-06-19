package com.schaefer.core.network

sealed class ResultWrapper<out T> {

    data class Success<out T>(
        val value: T
    ) : ResultWrapper<T>()

    data class GenericCodeError(
        val code: Int? = null,
        val error: ErrorResponse? = null
    ) : ResultWrapper<Nothing>()

    data class NetworkError(val exception: Throwable) : ResultWrapper<Nothing>()

    data class GenericError(val exception: Throwable) : ResultWrapper<Nothing>()
}