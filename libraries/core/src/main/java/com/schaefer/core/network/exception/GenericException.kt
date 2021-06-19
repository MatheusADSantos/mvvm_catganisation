package com.schaefer.core.network.exception

private const val GENERIC_EXCEPTION_MESSAGE =
    "Ops, there is something wrong."

internal class GenericException : Throwable(message = GENERIC_EXCEPTION_MESSAGE)