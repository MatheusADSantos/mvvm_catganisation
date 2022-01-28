package com.schaefer.home.domain.usecase

import io.reactivex.rxjava3.core.Single

interface GetBreedListUseCase<T> {
    suspend fun observeList(): Single<List<T>>
}