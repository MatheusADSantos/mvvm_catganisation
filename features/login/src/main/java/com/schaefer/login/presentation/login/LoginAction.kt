package com.schaefer.login.presentation.login

sealed class LoginAction {
    object NavigateToHome: LoginAction()
    data class ShowErrorMessage(val message: String): LoginAction()
}