package com.schaefer.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.schaefer.core.presentation.ViewModelAction
import com.schaefer.core.presentation.ViewModelState

open class ViewModelActionState<State : ViewModelState, Action : ViewModelAction>(
    initialState: State
): ViewModel(){
    private val viewModelState = MutableLiveData(initialState)
    private val viewModelAction = MutableLiveData<Action>()

    val state: LiveData<State> = viewModelState
    val action: LiveData<Action> = viewModelAction

    protected fun setState(state: State) {
        viewModelState.postValue(state)
    }

    protected open fun sendAction(action: Action) {
        viewModelAction.postValue(action)
    }
}