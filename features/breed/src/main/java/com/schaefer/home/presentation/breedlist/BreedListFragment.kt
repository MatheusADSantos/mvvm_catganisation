package com.schaefer.home.presentation.breedlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.schaefer.core.extension.layoutManagerFactory
import com.schaefer.home.R
import com.schaefer.home.databinding.FragmentBreedListBinding
import com.schaefer.home.presentation.breeddetails.BreedDetailsFragment
import com.schaefer.home.presentation.breedlist.adapter.BreedListAdapter
import com.schaefer.home.presentation.country.CountryDialogFragment
import com.schaefer.home.presentation.logout.logoutAlert
import com.schaefer.home.presentation.model.BreedItemVO
import com.schaefer.navigation.ContainerSingleActivity
import com.schaefer.navigation.breed.BreedNavigation
import com.schaefer.navigation.login.LoginNavigation
import hollowsoft.country.Country
import hollowsoft.country.extension.all
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

private const val ARG_COLUMN_COUNT = "column_count"

internal class BreedListFragment : Fragment() {
    private var columnCount = 1
    private val breedListRecyclerViewAdapter by lazy {
        BreedListAdapter(::openBreedDetails)
    }

    private val breedListViewModel: BreedListViewModel by viewModel()
    private val loginNavigation: LoginNavigation by inject()
    private val breedNavigation: BreedNavigation by inject()
    private val containerSingleActivity: ContainerSingleActivity by inject()

    private lateinit var binding: FragmentBreedListBinding
    private lateinit var countryDialogFragment: CountryDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreedListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupView()
        breedListViewModel.getBreedList()
    }

    private fun setupView() {
        with(binding) {
            rvBreedList.apply {
                layoutManager = layoutManagerFactory(columnCount)
                adapter = breedListRecyclerViewAdapter
            }

            toolbar.inflateMenu(R.menu.breed_list_menu)
            toolbar.menu.findItem(R.id.action_logout).setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_logout -> {
                        requireContext().logoutAlert(::navigateLogout)
                        false
                    }
                    else -> true
                }
            }

            editTextBreedListCountry.setOnClickListener {
                countryDialogFragment = CountryDialogFragment.newInstance { country ->
                    editTextBreedListCountry.setText(country.name)
                    breedListRecyclerViewAdapter.breedList =
                        breedListViewModel.filterList(country.id.substring(0, 2))
                    countryDialogFragment.dismiss()
                }
                countryDialogFragment.show(parentFragmentManager, CountryDialogFragment::class.simpleName)
            }

            textInputBreedListCountry.setEndIconOnClickListener {
                editTextBreedListCountry.setText(R.string.common_all)
                breedListRecyclerViewAdapter.breedList = breedListViewModel.getOriginalList()
            }
        }
    }

    private fun navigateLogout() {
        parentFragmentManager.beginTransaction()
            .replace(
                containerSingleActivity.containerId,
                loginNavigation.getLogoutFragment(shouldLogout = true)
            )
            .commit()
    }

    private fun setupObservers() {
        breedListViewModel.breedList.observe(viewLifecycleOwner) { list ->
            breedListRecyclerViewAdapter.breedList = list
            binding.rvBreedList.isVisible = true
            binding.progressBar.isVisible = false
        }
    }

    private fun openBreedDetails(itemVO: BreedItemVO) {
        breedNavigation.getBreedDetailsFragment(itemVO)?.let {
            parentFragmentManager.beginTransaction()
                .add(
                    containerSingleActivity.containerId,
                    it
                )
                .addToBackStack(BreedDetailsFragment::class.simpleName)
                .commit()
        }
    }

    companion object {

        fun newInstance(columnCount: Int) =
            BreedListFragment().apply {
                arguments = bundleOf(ARG_COLUMN_COUNT to columnCount)
            }
    }
}