package com.schaefer.home.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ImageVO(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
): Parcelable