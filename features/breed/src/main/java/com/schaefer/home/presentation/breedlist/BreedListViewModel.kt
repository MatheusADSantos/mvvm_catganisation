package com.schaefer.home.presentation.breedlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.schaefer.core.presentation.viewmodel.ViewModelActionState
import com.schaefer.home.domain.usecase.GetBreedListUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.model.CharacteristicItemVO
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import timber.log.Timber

internal class BreedListViewModel(
    val getBreedListUseCase: GetBreedListUseCase<BreedItemVO>
) : ViewModelActionState<BreedListState, BreedListAction>(BreedListState.Loading) {

    private val _breedList = MutableLiveData<List<BreedItemVO>>()

    fun getOriginalList() {
        sendListState(_breedList.value ?: emptyList())
    }

    fun navigateToDetails(itemVO: BreedItemVO) {
        sendAction(BreedListAction.NavigateToBreedDetails(itemVO))
    }

    fun navigateToLogout() {
        sendAction(BreedListAction.NavigateToLogout)
    }

    fun filterList(countryId: String) {
        val list = _breedList.value?.filter {
            it.country_code == countryId
        } ?: emptyList()

        sendListState(list)
    }

    fun getBreedList() {
        setState(BreedListState.Loading)
        viewModelScope.launch {
            getBreedListUseCase.observeList().subscribeOn(Schedulers.io())
                .subscribeBy(
                    onError = { handleError(it) },
                    onSuccess = { handleSuccess(it) },
                )
        }
    }

    private fun handleError(it: Throwable) {
        Timber.e(it)
        setState(BreedListState.Error)
    }

    private fun handleSuccess(it: List<BreedItemVO>) {
        _breedList.postValue(it)
        setState(BreedListState.HasContent(it))
    }

    private fun sendListState(list: List<BreedItemVO>) {
        if (list.isEmpty()) {
            setState(BreedListState.EmptyList)
        } else {
            setState(BreedListState.HasContent(list))
        }
    }
}