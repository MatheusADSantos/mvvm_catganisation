package com.schaefer.home.navigation

import androidx.fragment.app.Fragment
import com.schaefer.home.presentation.breedlist.BreedListFragment
import com.schaefer.navigation.breed.BreedNavigation

internal class BreedNavigationImpl: BreedNavigation {
    override fun getFragment(columnCount: Int): Fragment {
        return BreedListFragment.newInstance(columnCount)
    }
}