package com.schaefer.home.presentation.breedlist

import com.schaefer.core.presentation.ViewModelAction
import com.schaefer.home.presentation.model.BreedItemVO

internal sealed class BreedListAction : ViewModelAction {
    data class NavigateToBreedDetails(val itemVO: BreedItemVO): BreedListAction()
    object NavigateToLogout: BreedListAction()
}