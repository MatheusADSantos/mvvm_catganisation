package com.schaefer.webview.navigation

import androidx.fragment.app.Fragment
import com.schaefer.navigation.webview.WebViewNavigation
import com.schaefer.webview.WebViewFragment

internal class WebViewNavigationImpl: WebViewNavigation {
    override fun getFragment(url: String, toolbarTitle: String): Fragment {
        return WebViewFragment.newInstance(url, toolbarTitle)
    }

    override fun getFragmentName(): String {
        return WebViewFragment::class.java.simpleName
    }
}