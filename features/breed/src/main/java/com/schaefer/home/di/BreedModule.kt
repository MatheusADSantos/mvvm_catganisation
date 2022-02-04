package com.schaefer.home.di

import com.schaefer.home.data.api.TheCatAPI
import com.schaefer.home.data.datasource.BreedRemoteDataSource
import com.schaefer.home.data.datasource.BreedRemoteDataSourceImpl
import com.schaefer.home.data.repository.BreedRepositoryImpl
import com.schaefer.home.domain.repository.BreedRepository
import com.schaefer.home.domain.usecase.GetBreedListUseCase
import com.schaefer.home.domain.usecase.GetCharacteristicsUseCase
import com.schaefer.home.navigation.BreedNavigationImpl
import com.schaefer.home.presentation.breeddetails.BreedDetailsViewModel
import com.schaefer.home.presentation.breedlist.BreedListViewModel
import com.schaefer.home.presentation.breedlist.usecase.GetBreedListUseCaseImpl
import com.schaefer.home.presentation.breedlist.usecase.GetCharacteristicsUseCaseImpl
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.model.CharacteristicItemVO
import com.schaefer.navigation.breed.BreedNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val breedModule = module {
    single<TheCatAPI> {
        get<Retrofit>().create(TheCatAPI::class.java)
    }

    factory<BreedRemoteDataSource> { BreedRemoteDataSourceImpl(theCatAPI = get()) }

    factory<BreedRepository> { BreedRepositoryImpl(remoteDataSource = get()) }

    factory<GetBreedListUseCase<BreedItemVO>> { GetBreedListUseCaseImpl(repository = get()) }

    factory<GetCharacteristicsUseCase<BreedItemVO, CharacteristicItemVO>> {
        GetCharacteristicsUseCaseImpl()
    }

    viewModel { BreedListViewModel(getBreedListUseCase = get()) }

    viewModel { BreedDetailsViewModel(getCharacteristicsUseCase = get()) }

    factory<BreedNavigation> { BreedNavigationImpl() }
}