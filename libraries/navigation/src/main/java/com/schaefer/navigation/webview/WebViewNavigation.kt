package com.schaefer.navigation.webview

import androidx.fragment.app.Fragment

interface WebViewNavigation {
    fun getFragment(url: String, toolbarTitle: String): Fragment
    fun getFragmentName(): String
}