package com.schaefer.home.presentation.breedlist

import androidx.recyclerview.widget.DiffUtil
import com.schaefer.home.presentation.model.BreedItemVO

internal class BreedListDiffCallback(
    private val oldList: List<BreedItemVO>,
    private val newList: List<BreedItemVO>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}
