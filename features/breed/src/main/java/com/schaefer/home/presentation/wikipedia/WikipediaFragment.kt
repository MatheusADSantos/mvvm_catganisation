package com.schaefer.home.presentation.wikipedia

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.schaefer.home.R
import com.schaefer.home.databinding.FragmentWikipediaBinding
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.navigation.ContainerSingleActivity
import org.koin.android.ext.android.inject
import android.webkit.WebView

import android.app.ProgressDialog

import android.webkit.WebViewClient
import androidx.core.view.isVisible
import java.lang.Exception


private const val ARG_URL = "url"
private const val ARG_BREED_NAME = "name"

class WikipediaFragment : Fragment() {

    private lateinit var binding: FragmentWikipediaBinding
    private var urlWikipedia: String? = null
    private var breedName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            urlWikipedia = it.getString(ARG_URL)
            breedName = it.getString(ARG_BREED_NAME)
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
                title = breedName.orEmpty()

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

    private fun startWebView() {
        binding.progressBarWikipedia.isVisible = true
        binding.webViewWikipedia.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onLoadResource(view: WebView, url: String) {
                binding.progressBarWikipedia.isVisible = true
            }

            override fun onPageFinished(view: WebView, url: String) {
                binding.progressBarWikipedia.isVisible = false
            }
        }

        binding.webViewWikipedia.settings.apply {
            setJavaScriptEnabled(true)
            loadWithOverviewMode = true
            useWideViewPort = true
            builtInZoomControls = true
        }

        binding.webViewWikipedia.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        binding.webViewWikipedia.isScrollbarFadingEnabled = false
        urlWikipedia?.let { binding.webViewWikipedia.loadUrl(it) }
    }

    companion object {
        fun newInstance(url: String, breedName: String): WikipediaFragment {
            return WikipediaFragment().apply {
                arguments = bundleOf(
                    ARG_URL to url,
                    ARG_BREED_NAME to breedName
                )
            }
        }
    }
}