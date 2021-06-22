package com.schaefer.home.domain.usecase

import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.model.CharacteristicItemVO
import com.schaefer.home.presentation.model.Characteristics

internal class GetCharacteristicsListUseCase {
    operator fun invoke(breedItemVO: BreedItemVO): List<CharacteristicItemVO>{
        return listOf(
            CharacteristicItemVO(
                Characteristics.ADAPTABILITY.value,
                breedItemVO.adaptability
            ),
            CharacteristicItemVO(
                Characteristics.AFFECTION_LEVEL.value,
                breedItemVO.affection_level
            ),
            CharacteristicItemVO(
                Characteristics.CHILD_FRIENDLY.value,
                breedItemVO.child_friendly
            ),
            CharacteristicItemVO(
                Characteristics.DOG_FRIENDLY.value,
                breedItemVO.dog_friendly
            ),
            CharacteristicItemVO(
                Characteristics.ENERGY_LEVEL.value,
                breedItemVO.energy_level
            ),
            CharacteristicItemVO(
                Characteristics.GROOMING.value,
                breedItemVO.grooming
            ),
            CharacteristicItemVO(
                Characteristics.HEALTH_ISSUES.value,
                breedItemVO.health_issues
            ),
            CharacteristicItemVO(
                Characteristics.INTELLIGENCE.value,
                breedItemVO.intelligence
            ),
            CharacteristicItemVO(
                Characteristics.SHEDDING_LEVEL.value,
                breedItemVO.adaptability
            ),
            CharacteristicItemVO(
                Characteristics.SOCIAL_NEEDS.value,
                breedItemVO.social_needs
            ),
            CharacteristicItemVO(
                Characteristics.STRANGER_FRIENDLY.value,
                breedItemVO.stranger_friendly
            ),
            CharacteristicItemVO(
                Characteristics.VOCALISATION.value,
                breedItemVO.vocalisation
            ),
        )
    }
}