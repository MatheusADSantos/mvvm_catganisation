package com.schaefer.login.presentation.login

sealed class LoadingAction {
    object NavigateToHome: LoadingAction()
    data class ShowErrorMessage(val message: String): LoadingAction()
}