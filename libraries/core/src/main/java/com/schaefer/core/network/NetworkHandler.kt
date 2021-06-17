package com.schaefer.core.network

import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                ResultWrapper.GenericError(code, errorResponse)
            }
            else -> {
                ResultWrapper.GenericError(null, null)
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.let {
            val gsonAdapter = Gson().getAdapter(ErrorResponse::class.java)
            gsonAdapter.fromJson(it.string())
        }
    } catch (exception: Exception) {
        Timber.e(exception)
        null
    }
}