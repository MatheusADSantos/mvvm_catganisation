package com.schaefer.home.presentation.breeddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schaefer.home.domain.usecase.GetCharacteristicsListUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.model.CharacteristicItemVO
import com.schaefer.home.presentation.model.Characteristics

internal class BreedDetailsViewModel(
    val getCharacteristicsListUseCase: GetCharacteristicsListUseCase
) : ViewModel() {

    private val _characteristicsList = MutableLiveData<List<CharacteristicItemVO>>()
    val characteristicsList: LiveData<List<CharacteristicItemVO>> = _characteristicsList

    fun getCharacteristics(breedItemVO: BreedItemVO) {
        _characteristicsList.postValue(getCharacteristicsListUseCase(breedItemVO))
    }
}