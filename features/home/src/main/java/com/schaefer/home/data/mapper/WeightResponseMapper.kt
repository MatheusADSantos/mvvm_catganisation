package com.schaefer.home.data.mapper

import com.schaefer.home.data.model.WeightResponse
import com.schaefer.home.domain.model.WeightDomain

internal fun WeightResponse?.toDomain() : WeightDomain{
    return WeightDomain(
        this?.imperial.orEmpty(), this?.metric.orEmpty()
    )
}