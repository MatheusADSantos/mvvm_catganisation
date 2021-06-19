package com.schaefer.home.domain.repository

import com.schaefer.home.domain.model.BreedItemDomain
import io.reactivex.rxjava3.core.Single

internal interface BreedRepository {
    suspend fun getBreedList(): Single<List<BreedItemDomain>>
}