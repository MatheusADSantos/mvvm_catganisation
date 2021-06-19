package com.schaefer.navigation.login

import androidx.fragment.app.Fragment

interface LoginNavigation{
    fun getFragment(): Fragment

    fun getLogoutFragment(shouldLogout: Boolean = false): Fragment
}