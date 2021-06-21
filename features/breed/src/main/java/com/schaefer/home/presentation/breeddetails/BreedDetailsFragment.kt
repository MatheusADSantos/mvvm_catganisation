package com.schaefer.home.presentation.breeddetails

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.schaefer.core.extension.layoutManagerFactory
import com.schaefer.home.R
import com.schaefer.home.databinding.FragmentBreedDetailsBinding
import com.schaefer.home.presentation.breeddetails.adapter.CharacteristicsListAdapter
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.home.presentation.wikipedia.WikipediaFragment
import com.schaefer.navigation.ContainerSingleActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

private const val ARG_BREED_ITEM_VO = "breed_item_vo"

internal class BreedDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBreedDetailsBinding
    private var breedItemVO: BreedItemVO? = null
    private val characteristicsAdapter: CharacteristicsListAdapter by lazy {
        CharacteristicsListAdapter()
    }
    private val breedDetailsViewModel: BreedDetailsViewModel by viewModel()
    private val containerSingleActivity: ContainerSingleActivity by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            breedItemVO = it.getParcelable<BreedItemVO>(ARG_BREED_ITEM_VO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreedDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
        breedItemVO?.let { breedDetailsViewModel.getCharacteristics(it) }
    }

    private fun setupObservers() {
        breedDetailsViewModel.characteristicsList.observe(viewLifecycleOwner) {
            characteristicsAdapter.characterList = it
        }
    }

    private fun setupView() {
        with(binding.includeContent) {
            tvBreedDetailsDescriptionBody.text = breedItemVO?.description
            tvBreedDetailsTemperamentBody.text = breedItemVO?.temperament
            rvBreedDetailsLevels.apply {
                layoutManager = layoutManagerFactory(1)
                adapter = characteristicsAdapter
            }

            breedItemVO?.country_code.let{ countryId ->
                chipGroup.addView(
                    Chip(binding.root.context).apply {
                        text = resources.getString(R.string.country_code_chip, countryId)
                        isClickable = false
                    }
                )
            }
        }

        with(binding.toolbar) {
            title = breedItemVO?.name
            setTitleTextColor(Color.WHITE)
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        Glide.with(binding.root.context)
            .load(breedItemVO?.imageResponse?.url)
            .into(binding.ivBreedItem)

        binding.fab.setOnClickListener {
            breedItemVO?.let {
                parentFragmentManager.beginTransaction().add(
                    containerSingleActivity.containerId,
                    WikipediaFragment.newInstance(it.wikipedia_url, it.name)
                ).addToBackStack(WikipediaFragment::class.simpleName).commit()
            }
        }
    }

    companion object {
        fun newInstance(breedItemVO: BreedItemVO) =
            BreedDetailsFragment().apply {
                arguments = bundleOf(ARG_BREED_ITEM_VO to breedItemVO)
            }
    }
}