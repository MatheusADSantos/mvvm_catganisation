package com.schaefer.home.navigation

import androidx.fragment.app.Fragment
import com.schaefer.home.presentation.breeddetails.BreedDetailsFragment
import com.schaefer.home.presentation.breedlist.BreedListFragment
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.navigation.breed.BreedNavigation

internal class BreedNavigationImpl : BreedNavigation {
    override fun getBreedListFragment(columnCount: Int): Fragment {
        return BreedListFragment.newInstance(columnCount)
    }

    override fun <T> getBreedDetailsFragment(breedParcelable: T): Fragment? {
        return runCatching {
            BreedDetailsFragment.newInstance(breedParcelable as BreedItemVO)
        }.getOrNull()
    }
}