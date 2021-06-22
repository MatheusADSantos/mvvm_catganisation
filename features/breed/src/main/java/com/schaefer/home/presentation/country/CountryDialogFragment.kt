package com.schaefer.home.presentation.country

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.schaefer.core.extension.layoutManagerFactory
import com.schaefer.home.R
import com.schaefer.home.databinding.FragmentCountryListBinding
import com.schaefer.home.presentation.country.adapter.CountryAdapter
import hollowsoft.country.Country
import hollowsoft.country.extension.all

class CountryDialogFragment(
    private val listener: (Country) -> Unit
) : DialogFragment() {

    private lateinit var binding: FragmentCountryListBinding
    private val countriesAdapter: CountryAdapter by lazy {
        CountryAdapter(
            listener = listener,
            list = Country.all()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCountries.apply {
            adapter = countriesAdapter
            layoutManager = layoutManagerFactory(1)
        }
    }

    companion object {

        fun newInstance(listener: (Country) -> Unit): CountryDialogFragment {
            return CountryDialogFragment(listener)
        }
    }
}