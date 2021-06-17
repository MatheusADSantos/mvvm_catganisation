package com.schaefer.home.navigation

import androidx.fragment.app.Fragment
import com.schaefer.home.CatFragment
import com.schaefer.navigation.home.HomeNavigation

class HomeNavigationImpl: HomeNavigation {
    override fun getFragment(): Fragment {
        return CatFragment()
    }
}