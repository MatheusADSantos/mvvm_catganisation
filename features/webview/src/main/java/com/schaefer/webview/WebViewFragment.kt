package com.schaefer.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.schaefer.webview.databinding.FragmentWikipediaBinding

private const val ARG_URL = "url"
private const val ARG_BREED_NAME = "name"

internal class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWikipediaBinding
    private var urlPage: String? = null
    private var toolbarTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            urlPage = it.getString(ARG_URL)
            toolbarTitle = it.getString(ARG_BREED_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWikipediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbarWikipedia.apply {
                title = toolbarTitle.orEmpty()

                navigationIcon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_arrow_back_24
                )
                setNavigationOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
        }
        startWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun startWebView() {
        showLoading()
        with(binding) {
            webViewWikipedia.webViewClient = WebViewClient(::showLoading)

            webViewWikipedia.settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                builtInZoomControls = true
            }

            webViewWikipedia.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
            webViewWikipedia.isScrollbarFadingEnabled = false
            urlPage?.let { webViewWikipedia.loadUrl(it) }
        }
    }

    private fun showLoading(isToShowLoading: Boolean = true) {
        with(binding) {
            webViewWikipedia.isVisible = isToShowLoading.not()
            progressBarWikipedia.isVisible = isToShowLoading
        }
    }

    companion object {
        fun newInstance(url: String, toolbarTitle: String): WebViewFragment {
            return WebViewFragment().apply {
                arguments = bundleOf(
                    ARG_URL to url,
                    ARG_BREED_NAME to toolbarTitle
                )
            }
        }
    }
}