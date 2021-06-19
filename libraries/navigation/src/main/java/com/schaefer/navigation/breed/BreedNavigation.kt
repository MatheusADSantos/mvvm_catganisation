package com.schaefer.navigation.breed

import androidx.fragment.app.Fragment

interface BreedNavigation {
    fun getBreedListFragment(columnCount: Int): Fragment

    fun <T> getBreedDetailsFragment(breedParcelable: T): Fragment?
}