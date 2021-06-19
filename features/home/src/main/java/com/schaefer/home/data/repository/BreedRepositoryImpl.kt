package com.schaefer.home.data.repository

import com.schaefer.core.network.ResultWrapper
import com.schaefer.core.network.safeApiCall
import com.schaefer.home.data.datasource.BreedRemoteDataSource
import com.schaefer.home.data.mapper.toDomain
import com.schaefer.home.domain.model.BreedItemDomain
import com.schaefer.home.domain.repository.BreedRepository
import io.reactivex.rxjava3.core.Single

internal class BreedRepositoryImpl(private val remoteDataSource: BreedRemoteDataSource) : BreedRepository {
    override suspend fun getBreedList(): Single<List<BreedItemDomain>> {
        return safeApiCall {
            remoteDataSource.getBreedList()
        }.let { result->
            when (result) {
                is ResultWrapper.Success -> result.value.map { breedResponse ->
                    breedResponse.map {
                        it.toDomain()
                    }
                }
                is ResultWrapper.GenericCodeError -> Single.error(result.error)
                is ResultWrapper.GenericError -> Single.error(result.exception)
                is ResultWrapper.NetworkError -> Single.error(result.exception)
            }
        }
    }
}