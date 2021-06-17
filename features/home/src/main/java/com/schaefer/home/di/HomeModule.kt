package com.schaefer.home.di

import com.schaefer.home.navigation.HomeNavigationImpl
import com.schaefer.navigation.home.HomeNavigation
import org.koin.dsl.module

val homeModule = module {
    factory<HomeNavigation> { HomeNavigationImpl() }
}