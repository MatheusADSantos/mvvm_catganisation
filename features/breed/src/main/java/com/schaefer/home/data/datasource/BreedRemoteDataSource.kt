package com.schaefer.home.data.datasource

import com.schaefer.home.data.model.BreedResponse
import io.reactivex.rxjava3.core.Single

internal interface BreedRemoteDataSource {
    fun getBreedList(): Single<BreedResponse>
}