package com.schaefer.core.network

data class ErrorResponse(
    val errorDescription: String,
    val causes: Map<String, String> = emptyMap()
)