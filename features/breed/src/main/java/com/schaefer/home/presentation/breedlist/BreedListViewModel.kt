package com.schaefer.home.presentation.breedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.schaefer.core.presentation.viewmodel.ViewModelActionState
import com.schaefer.home.domain.usecase.GetBreedListUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


internal class BreedListViewModel(
    val getBreedListUseCase: GetBreedListUseCase
) : ViewModelActionState<BreedListState, BreedListAction>(BreedListState.Loading) {

    private val _breedList = MutableLiveData<List<BreedItemVO>>()
    val breedList: LiveData<List<BreedItemVO>> = _breedList

    fun getBreedList() {
        setState(BreedListState.Loading)
        viewModelScope.launch {
            getBreedListUseCase()
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onError = { handleError(it) },
                    onSuccess = { handleSuccess(it) },
                )
        }
    }

    private fun handleError(it: Throwable) {
        it.printStackTrace()
        setState(BreedListState.Error)
    }

    private fun handleSuccess(it: List<BreedItemVO>) {
        _breedList.postValue(it)
        setState(BreedListState.HasContent(it))
    }

    fun filterList(countryId: String) {
        val list = breedList.value?.filter {
            it.country_code == countryId
        } ?: emptyList()

        sendListState(list)
    }

    private fun sendListState(list: List<BreedItemVO>) {
        if (list.isEmpty()) {
            setState(BreedListState.EmptyList)
        } else {
            setState(BreedListState.HasContent(list))
        }
    }

    fun getOriginalList() {
        sendListState(breedList.value ?: emptyList())
    }
}