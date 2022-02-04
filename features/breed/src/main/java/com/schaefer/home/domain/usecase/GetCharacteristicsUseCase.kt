package com.schaefer.home.domain.usecase

interface GetCharacteristicsUseCase<I, O> {
    fun getList(input: I): List<O>
}