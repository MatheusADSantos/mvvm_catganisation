package com.schaefer.home.presentation.mapper

import com.schaefer.home.domain.model.WeightDomain
import com.schaefer.home.presentation.model.WeightVO

internal fun WeightDomain.toVO() : WeightVO{
    return WeightVO(
        imperial, metric
    )
}