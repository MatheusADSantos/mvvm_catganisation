package com.schaefer.home.presentation.breedlist.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.schaefer.home.databinding.LayoutBreedItemBinding
import com.schaefer.home.presentation.breedlist.BreedListDiffCallback

import com.schaefer.home.presentation.model.BreedItemVO

internal class BreedListRecyclerViewAdapter :
    RecyclerView.Adapter<BreedListRecyclerViewAdapter.BreedViewHolder>() {

    var breedList = emptyList<BreedItemVO>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                BreedListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = LayoutBreedItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        with(holder) {
            with(breedList[position]) {
                this.let { breed ->
                    with(binding) {
                        tvBreedNameItem.text = breed.name
                        tvBreedDescriptionItem.text = breed.description

                        Glide.with(holder.itemView)
                            .load(breed.imageResponse.url)
                            .into(binding.ivBreedItem)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = breedList.size

    inner class BreedViewHolder(
        val binding: LayoutBreedItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}