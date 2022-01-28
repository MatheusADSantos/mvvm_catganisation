package com.schaefer.webview

import android.webkit.WebView
import android.webkit.WebViewClient

internal data class WebViewClient (val showLoading: (Boolean) -> Unit): WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onLoadResource(view: WebView, url: String) = Unit

    override fun onPageFinished(view: WebView, url: String) {
        showLoading(false)
    }
}