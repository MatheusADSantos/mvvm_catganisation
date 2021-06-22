package com.schaefer.core.resource

import android.content.Context

data class ResourcesProvider(val context: Context) {
    fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }
}