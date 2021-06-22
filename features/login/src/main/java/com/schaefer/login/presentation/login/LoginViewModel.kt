package com.schaefer.login.presentation.login

import com.schaefer.core.resource.ResourcesProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.schaefer.login.R
import java.lang.Exception

class LoginViewModel(val resourcesProvider: ResourcesProvider) : ViewModel() {

    private val _action = MutableLiveData<LoginAction>()
    val action: LiveData<LoginAction> = _action

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    fun updateUser(firebaseUser: FirebaseUser?) {
        _user.postValue(firebaseUser)
        firebaseUser?.let {
            _action.postValue(LoginAction.NavigateToHome)
        }
    }

    fun handleTask(isSuccessful: Boolean, exception: Exception?) {
        exception?.let {
            _action.postValue(
                LoginAction.ShowErrorMessage(
                    it.message ?: resourcesProvider.getString(
                        R.string.login_error_message
                    )
                )
            )
        }
        isSuccessful.takeIf { it }.let { _action.postValue(LoginAction.NavigateToHome) }
    }
}