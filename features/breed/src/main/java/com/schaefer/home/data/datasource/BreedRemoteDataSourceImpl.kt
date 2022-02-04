package com.schaefer.home.data.datasource

import com.schaefer.home.data.api.TheCatAPI
import com.schaefer.home.data.model.BreedResponse
import io.reactivex.rxjava3.core.Single

internal class BreedRemoteDataSourceImpl(val theCatAPI: TheCatAPI): BreedRemoteDataSource {

    override fun getBreedList(): Single<BreedResponse> {
        return theCatAPI.getBreeds()
    }
}
