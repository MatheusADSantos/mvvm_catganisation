package com.schaefer.home.data.mapper

import com.schaefer.home.data.model.BreedItemResponse
import com.schaefer.home.domain.model.BreedItemDomain

internal fun BreedItemResponse.toDomain(): BreedItemDomain {
    return BreedItemDomain(
        adaptability ?: Int.MIN_VALUE,
        affection_level ?: Int.MIN_VALUE,
        alt_names.orEmpty(),
        bidability?: Int.MIN_VALUE,
        cat_friendly?: Int.MIN_VALUE,
        cfa_url.orEmpty(),
        child_friendly?: Int.MIN_VALUE,
        country_code.orEmpty(),
        country_codes.orEmpty(),
        description.orEmpty(),
        dog_friendly?: Int.MIN_VALUE,
        energy_level?: Int.MIN_VALUE,
        experimental?: Int.MIN_VALUE,
        grooming?: Int.MIN_VALUE,
        hairless?: Int.MIN_VALUE,
        health_issues?: Int.MIN_VALUE,
        hypoallergenic?: Int.MIN_VALUE,
        id.orEmpty(),
        image.toDomain(),
        indoor?: Int.MIN_VALUE,
        intelligence?: Int.MIN_VALUE,
        lap?: Int.MIN_VALUE,
        life_span.orEmpty(),
        name.orEmpty(),
        natural?: Int.MIN_VALUE,
        origin.orEmpty(),
        rare?: Int.MIN_VALUE,
        reference_image_id.orEmpty(),
        rex?: Int.MIN_VALUE,
        shedding_level?: Int.MIN_VALUE,
        short_legs?: Int.MIN_VALUE,
        social_needs?: Int.MIN_VALUE,
        stranger_friendly?: Int.MIN_VALUE,
        suppressed_tail?: Int.MIN_VALUE,
        temperament.orEmpty(),
        vcahospitals_url.orEmpty(),
        vetstreet_url.orEmpty(),
        vocalisation ?: Int.MIN_VALUE,
        weight.toDomain(),
        wikipedia_url.orEmpty()
    )
}
