package com.schaefer.home.presentation.breedlist

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import com.schaefer.core.extension.layoutManagerFactory
import com.schaefer.home.R
import com.schaefer.home.databinding.FragmentBreedListBinding
import com.schaefer.home.presentation.breedlist.adapter.BreedListRecyclerViewAdapter
import com.schaefer.home.presentation.model.BreedItemVO
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.schaefer.navigation.ContainerSingleActivity
import com.schaefer.navigation.login.LoginNavigation
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

private const val ARG_COLUMN_COUNT = "column-count"

internal class BreedListFragment : Fragment() {
    private var columnCount = 1
    private val breedListRecyclerViewAdapter by lazy {
        BreedListRecyclerViewAdapter()
    }

    private val breedListViewModel: BreedListViewModel by viewModel()
    private val loginNavigation: LoginNavigation by inject()
    private val containerSingleActivity: ContainerSingleActivity by inject()

    private lateinit var binding: FragmentBreedListBinding
    private lateinit var spinner: Spinner

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

            toolbar.inflateMenu(R.menu.home_menu)
            toolbar.menu.findItem(R.id.action_logout).setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_logout -> {
                        logoutAlert()
                        false
                    }
                    else -> true
                }
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

            populateSpinner(
                getSetToFilter(list)
            )
        }
    }

    private fun getSetToFilter(list: List<BreedItemVO>): Set<String> {
        val mutableSet = list.map {
            it.country_code
        }.toMutableSet()
        mutableSet.add("-")
        return mutableSet
    }

    private fun populateSpinner(list: Set<String>) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list.toList())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        if (::spinner.isInitialized) {
            spinner.adapter = adapter
            spinner.setSelection(
                list.indexOf(
                    list.last()
                )
            )
        }
    }

    private fun logoutAlert() {
        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle(R.string.logout_dialog_title)
            setPositiveButton(
                R.string.common_yes
            ) { _: DialogInterface, _: Int ->
                navigateLogout()
            }
            setNegativeButton(
                R.string.common_cancel
            ) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
            show().also {
                it.getButton(AlertDialog.BUTTON_NEGATIVE)
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                it.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            }
        }
    }

    companion object {

        fun newInstance(columnCount: Int) =
            BreedListFragment().apply {
                arguments = bundleOf(ARG_COLUMN_COUNT to columnCount)
            }
    }
}