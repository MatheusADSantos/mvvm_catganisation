package com.schaefer.navigation.breed

import androidx.fragment.app.Fragment

interface BreedNavigation{
    fun getFragment(columnCount: Int): Fragment
}