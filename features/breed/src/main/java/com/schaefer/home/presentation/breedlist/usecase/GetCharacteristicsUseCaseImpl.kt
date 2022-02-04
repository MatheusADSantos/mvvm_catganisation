package com.schaefer.home.presentation.breedlist.usecase

import com.schaefer.home.domain.usecase.GetCharacteristicsUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.model.CharacteristicItemVO
import com.schaefer.home.presentation.model.Characteristics

internal class GetCharacteristicsUseCaseImpl :
    GetCharacteristicsUseCase<BreedItemVO, CharacteristicItemVO> {

    override fun getList(input: BreedItemVO): List<CharacteristicItemVO> {
        return listOf(
            CharacteristicItemVO(Characteristics.ADAPTABILITY.value, input.adaptability),
            CharacteristicItemVO(Characteristics.AFFECTION_LEVEL.value, input.affection_level),
            CharacteristicItemVO(Characteristics.CHILD_FRIENDLY.value, input.child_friendly),
            CharacteristicItemVO(Characteristics.DOG_FRIENDLY.value, input.dog_friendly),
            CharacteristicItemVO(Characteristics.ENERGY_LEVEL.value, input.energy_level),
            CharacteristicItemVO(Characteristics.GROOMING.value, input.grooming),
            CharacteristicItemVO(Characteristics.HEALTH_ISSUES.value, input.health_issues),
            CharacteristicItemVO(Characteristics.INTELLIGENCE.value, input.intelligence),
            CharacteristicItemVO(Characteristics.SHEDDING_LEVEL.value, input.adaptability),
            CharacteristicItemVO(Characteristics.SOCIAL_NEEDS.value, input.social_needs),
            CharacteristicItemVO(Characteristics.STRANGER_FRIENDLY.value, input.stranger_friendly),
            CharacteristicItemVO(Characteristics.VOCALISATION.value, input.vocalisation),
        )
    }
}