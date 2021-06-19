package com.schaefer.home.domain.usecase

import com.schaefer.home.domain.mapper.toVO
import com.schaefer.home.domain.repository.BreedRepository
import com.schaefer.home.presentation.model.BreedItemVO
import io.reactivex.rxjava3.core.Single

internal class GetBreedListUseCase(private val repository: BreedRepository) {

    suspend operator fun invoke(): Single<List<BreedItemVO>> {
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