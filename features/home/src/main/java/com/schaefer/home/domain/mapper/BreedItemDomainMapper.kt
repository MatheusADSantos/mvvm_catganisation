package com.schaefer.home.domain.mapper

import com.schaefer.home.domain.model.BreedItemDomain
import com.schaefer.home.presentation.model.BreedItemVO

internal fun BreedItemDomain.toVO(): BreedItemVO {
    return BreedItemVO(
        adaptability,
        affection_level,
        alt_names,
        bidability,
        cat_friendly,
        cfa_url,
        child_friendly,
        country_code,
        country_codes,
        description,
        dog_friendly,
        energy_level,
        experimental,
        grooming,
        hairless,
        health_issues,
        hypoallergenic,
        id,
        imageResponse.toVO(),
        indoor,
        intelligence,
        lap,
        life_span,
        name,
        natural,
        origin,
        rare,
        reference_image_id,
        rex,
        shedding_level,
        short_legs,
        social_needs,
        stranger_friendly,
        suppressed_tail,
        temperament,
        vcahospitals_url,
        vetstreet_url,
        vocalisation,
        weightResponse.toVO(),
        wikipedia_url
    )
}