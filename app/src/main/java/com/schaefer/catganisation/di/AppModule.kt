package com.schaefer.catganisation.di

import com.schaefer.catganisation.R
import com.schaefer.core.resource.ResourcesProvider
import com.schaefer.navigation.ContainerSingleActivity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { ContainerSingleActivity(R.id.containerMain) }

    factory { ResourcesProvider(androidContext()) }
}