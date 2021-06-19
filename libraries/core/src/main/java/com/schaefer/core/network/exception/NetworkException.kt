package com.schaefer.core.network.exception

private const val NETWORK_EXCEPTION_MESSAGE =
    "Ops, there is something wrong. Please, check your connection and try again."

internal class NetworkException : Throwable(message = NETWORK_EXCEPTION_MESSAGE)