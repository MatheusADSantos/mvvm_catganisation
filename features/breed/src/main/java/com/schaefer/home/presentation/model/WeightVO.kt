package com.schaefer.home.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class WeightVO(
    val imperial: String,
    val metric: String
): Parcelable