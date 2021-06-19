package com.schaefer.navigation.home

import androidx.fragment.app.Fragment

interface HomeNavigation{
    fun getFragment(columnCount: Int): Fragment
}