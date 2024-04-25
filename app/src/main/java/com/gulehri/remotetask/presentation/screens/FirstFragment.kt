package com.gulehri.remotetask.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gulehri.remotetask.data.remote.NetworkResult
import com.gulehri.remotetask.databinding.FragmentFirstBinding
import com.gulehri.remotetask.presentation.viewmodels.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class FirstFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val countryViewModel by activityViewModels<CountryViewModel>()
    private var _allAdapter: AllAdapter? = null
    private val allAdapter get() = _allAdapter!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countryViewModel.allCountries.collect {

                    when (it) {
                        NetworkResult.Cancel -> {

                        }

                        NetworkResult.Empty -> {

                        }

                        is NetworkResult.Error -> {
                            Toast.makeText(binding.root.context, "${it.error}", Toast.LENGTH_SHORT)
                                .show()

                        }

                        NetworkResult.Loading -> {
                            Toast.makeText(binding.root.context, "Loading Data...", Toast.LENGTH_SHORT)
                                .show()
                        }

                        is NetworkResult.Success -> {
                            allAdapter.submitList(it.data.sortedBy{it.name.common})

                        }
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        _allAdapter = AllAdapter()
        binding.recyclerView.adapter = allAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}