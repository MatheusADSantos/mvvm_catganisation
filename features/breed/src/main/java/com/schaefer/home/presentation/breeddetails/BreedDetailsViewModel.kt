package com.schaefer.home.presentation.breeddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schaefer.home.domain.usecase.GetCharacteristicsUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.model.CharacteristicItemVO

internal class BreedDetailsViewModel(
    private val getCharacteristicsUseCase: GetCharacteristicsUseCase<BreedItemVO, CharacteristicItemVO>
) : ViewModel() {

    private val _characteristicsList = MutableLiveData<List<CharacteristicItemVO>>()
    val characteristicsList: LiveData<List<CharacteristicItemVO>> = _characteristicsList

    fun getCharacteristics(breedItemVO: BreedItemVO) {
        _characteristicsList.postValue(getCharacteristicsUseCase.getList(breedItemVO))
    }
}