package com.schaefer.home.presentation.breeddetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.schaefer.core.extension.layoutManagerFactory
import com.schaefer.home.R
import com.schaefer.home.databinding.FragmentBreedDetailsBinding
import com.schaefer.home.presentation.breeddetails.adapter.CharacteristicsListAdapter
import com.schaefer.home.presentation.model.BreedItemVO
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_BREED_ITEM_VO = "breed_item_vo"

internal class BreedDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBreedDetailsBinding
    private var breedItemVO: BreedItemVO? = null
    private val characteristicsAdapter: CharacteristicsListAdapter by lazy {
        CharacteristicsListAdapter()
    }
    private val breedDetailsViewModel: BreedDetailsViewModel by viewModel()

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
        }

        with(binding.toolbar) {
            title = breedItemVO?.name
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }

        Glide.with(binding.root.context)
            .load(breedItemVO?.imageResponse?.url)
            .into(binding.ivBreedItem)
    }

    companion object {
        fun newInstance(breedItemVO: BreedItemVO) =
            BreedDetailsFragment().apply {
                arguments = bundleOf(ARG_BREED_ITEM_VO to breedItemVO)
            }
    }
}