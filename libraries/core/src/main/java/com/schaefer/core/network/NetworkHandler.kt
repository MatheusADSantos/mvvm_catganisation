package com.schaefer.core.network

import com.google.gson.Gson
import com.schaefer.core.network.exception.GenericException
import com.schaefer.core.network.exception.NetworkException
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
            is IOException -> {
                ResultWrapper.NetworkError(NetworkException())
            }
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable)
                ResultWrapper.GenericCodeError(code, errorResponse)
            }
            else -> {
                ResultWrapper.GenericError(GenericException())
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