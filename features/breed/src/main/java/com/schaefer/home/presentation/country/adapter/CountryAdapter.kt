package com.schaefer.home.presentation.country.adapter

/*
 * Copyright 2017 Igor Morais
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.schaefer.home.databinding.LayoutCountryItemBinding
import hollowsoft.country.Country
import hollowsoft.country.extension.image

/**
 * @author Igor Morais
 * Reference: https://github.com/MoraisIgor/Country/blob/master/Sample/app/src/main/java/hollowsoft/sample/country/view/CountryAdapter.kt
 */
class CountryAdapter(
    val listener: (Country) -> Unit,
    private val list: List<Country>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding = LayoutCountryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                this.let { country ->
                    with(binding) {
                        name.text = country.name

                        Glide.with(holder.itemView)
                            .load(country.image)
                            .into(image)

                        linearParent.setOnClickListener {
                            listener(country)
                        }
                    }
                }
            }
        }
    }

    inner class CountryViewHolder(
        val binding: LayoutCountryItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
