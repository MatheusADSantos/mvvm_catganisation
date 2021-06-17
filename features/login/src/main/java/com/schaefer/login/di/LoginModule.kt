package com.schaefer.login.di

import com.schaefer.login.navigation.LoginNavigationImpl
import com.schaefer.login.presentation.login.LoginViewModel
import com.schaefer.navigation.login.LoginNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory<LoginNavigation> { LoginNavigationImpl() }

    viewModel { LoginViewModel(resourcesProvider = get()) }
}