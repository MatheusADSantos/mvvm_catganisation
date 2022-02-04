package com.schaefer.home.data.mapper

import com.schaefer.home.data.model.ImageResponse
import com.schaefer.home.domain.model.ImageDomain

internal fun ImageResponse?.toDomain(): ImageDomain {
    return ImageDomain(
        this?.height ?: Int.MIN_VALUE,
        this?.id.orEmpty(),
        this?.url.orEmpty(),
        this?.width ?: Int.MIN_VALUE
    )
}
