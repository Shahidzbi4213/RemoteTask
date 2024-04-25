package com.gulehri.remotetask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulehri.remotetask.data.repositories.CountriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Created by Shahid Iqbal on 4/26/2024.
 */

@HiltViewModel
class CountryViewModel @Inject constructor(private val repo: CountriesRepo) : ViewModel() {

    val allCountries get() = repo.countries
    val countryByName get() = repo.country

    init {
        getAllCountries()
    }


    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllCountries()
        }
    }

    fun getCountryByName(countryName: String = "pakistan") {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getCountryByName(countryName)
        }
    }
}