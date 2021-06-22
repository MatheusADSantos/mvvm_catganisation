package com.schaefer.home.presentation.breedlist

import com.schaefer.core.presentation.ViewModelState
import com.schaefer.home.presentation.model.BreedItemVO

internal sealed class BreedListState: ViewModelState {
    object Loading: BreedListState()

    object EmptyList: BreedListState()

    object Error: BreedListState()

    data class HasContent(val list: List<BreedItemVO>): BreedListState()
}