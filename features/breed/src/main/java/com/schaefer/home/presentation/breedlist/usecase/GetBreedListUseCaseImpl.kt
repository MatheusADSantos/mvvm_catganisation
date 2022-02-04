package com.schaefer.home.presentation.breedlist.usecase

import com.schaefer.home.presentation.mapper.toVO
import com.schaefer.home.domain.repository.BreedRepository
import com.schaefer.home.domain.usecase.GetBreedListUseCase
import com.schaefer.home.presentation.model.BreedItemVO
import io.reactivex.rxjava3.core.Single

internal class GetBreedListUseCaseImpl(
    private val repository: BreedRepository
) : GetBreedListUseCase<BreedItemVO> {

    override suspend fun observeList(): Single<List<BreedItemVO>> {
        return runCatching {
            repository.getBreedList().map { breedDomain ->
                breedDomain.map {
                    it.toVO()
                }
            }
        }.onFailure {
            throw GetBreedListUseCaseException(it.message, it.cause)
        }.getOrDefault(
            Single.just(emptyList())
        )
    }
}

private class GetBreedListUseCaseException(
    override val message: String?,
    override val cause: Throwable?
) : Exception()