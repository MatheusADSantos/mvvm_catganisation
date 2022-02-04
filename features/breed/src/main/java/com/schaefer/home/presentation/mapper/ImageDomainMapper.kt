package com.schaefer.home.presentation.mapper

import com.schaefer.home.domain.model.ImageDomain
import com.schaefer.home.presentation.model.ImageVO

internal fun ImageDomain.toVO(): ImageVO {
    return ImageVO(height, id, url, width)
}