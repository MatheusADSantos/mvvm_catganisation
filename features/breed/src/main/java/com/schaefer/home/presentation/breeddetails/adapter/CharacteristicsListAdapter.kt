package com.schaefer.home.presentation.breeddetails.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.schaefer.home.databinding.LayoutBreedItemBinding
import com.schaefer.home.databinding.LayoutLevelsItemBinding

import com.schaefer.home.presentation.model.CharacteristicItemVO

internal class CharacteristicsListAdapter : RecyclerView.Adapter<CharacteristicsListAdapter.CharacteristicsViewHolder>() {

    var characterList = emptyList<CharacteristicItemVO>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                CharacteristicsListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicsViewHolder {
        val binding = LayoutLevelsItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacteristicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacteristicsViewHolder, position: Int) {
        with(holder) {
            with(characterList[position]) {
                this.let { characteristic ->
                    with(binding) {
                        tvLevelTitle.text = characteristic.title
                        ratingLevelValue.rating = characteristic.value.toFloat()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = characterList.size

    inner class CharacteristicsViewHolder(
        val binding: LayoutLevelsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}