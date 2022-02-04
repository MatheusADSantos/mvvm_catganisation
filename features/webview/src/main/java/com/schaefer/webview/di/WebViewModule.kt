package com.schaefer.webview.di

import com.schaefer.navigation.webview.WebViewNavigation
import com.schaefer.webview.navigation.WebViewNavigationImpl
import org.koin.dsl.module

val webViewModule = module {
    factory<WebViewNavigation> { WebViewNavigationImpl() }
}