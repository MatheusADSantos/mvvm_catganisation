package com.schaefer.home.di

import com.schaefer.home.data.api.TheCatAPI
import com.schaefer.home.data.datasource.BreedRemoteDataSource
import com.schaefer.home.data.datasource.BreedRemoteDataSourceImpl
import com.schaefer.home.data.repository.BreedRepositoryImpl
import com.schaefer.home.domain.repository.BreedRepository
import com.schaefer.home.domain.usecase.GetBreedListUseCase
import com.schaefer.home.navigation.HomeNavigationImpl
import com.schaefer.home.presentation.breedlist.BreedListViewModel
import com.schaefer.navigation.home.HomeNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule = module {
    single<TheCatAPI> {
        get<Retrofit>().create(TheCatAPI::class.java)
    }

    factory<BreedRemoteDataSource> { BreedRemoteDataSourceImpl(theCatAPI = get()) }

    factory<BreedRepository> { BreedRepositoryImpl(remoteDataSource = get()) }

    factory { GetBreedListUseCase(repository = get()) }

    viewModel { BreedListViewModel(getBreedListUseCase = get()) }

    factory<HomeNavigation> { HomeNavigationImpl() }
}