package com.schaefer.home.presentation.breedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schaefer.home.domain.usecase.GetBreedListUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


internal class BreedListViewModel(val getBreedListUseCase: GetBreedListUseCase) : ViewModel() {

    private val _breedList = MutableLiveData<List<BreedItemVO>>()
    val breedList: LiveData<List<BreedItemVO>> = _breedList

    fun getBreedList() {
        viewModelScope.launch {
            getBreedListUseCase()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onError = {
                        print(it.toString())
                        it.printStackTrace()
                    },
                    onSuccess = {
                        print(it.toString())
                        _breedList.postValue(it)
                    },
                )
        }
    }
}