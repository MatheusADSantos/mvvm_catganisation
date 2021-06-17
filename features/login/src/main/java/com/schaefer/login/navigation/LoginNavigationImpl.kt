package com.schaefer.login.navigation

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.schaefer.login.presentation.login.LoginFragment
import com.schaefer.navigation.login.LoginNavigation

class LoginNavigationImpl: LoginNavigation {
    override fun getFragment(): Fragment {
        return LoginFragment()
    }
}