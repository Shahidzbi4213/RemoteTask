package com.gulehri.remotetask.data.repositories

import com.gulehri.remotetask.data.remote.ApiService
import com.gulehri.remotetask.data.remote.NetworkResult
import com.gulehri.remotetask.data.remote.SafeApi
import com.gulehri.remotetask.models.Countries
import com.gulehri.remotetask.models.CountryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


/*
 * Created by Shahid Iqbal on 4/26/2024.
 */

class CountriesRepo @Inject constructor(private val apiService: ApiService) {

    private var _countriesResult = MutableStateFlow<NetworkResult<Countries>>(NetworkResult.Empty)
    val countries get() = _countriesResult.asStateFlow()

    private var _countryResult = MutableStateFlow<NetworkResult<CountryItem>>(NetworkResult.Empty)
    val country get() = _countryResult.asStateFlow()


    suspend fun getAllCountries() {

        _countriesResult.value = NetworkResult.Loading

        val data = SafeApi.apiCall {
            apiService.getAllCountries()
        }

        _countriesResult.value = data


    }

    suspend fun getCountryByName(countryName: String = "pakistan") {

        _countryResult.value = NetworkResult.Loading

        val data = SafeApi.apiCall {
            apiService.getCountryByName(countryName.lowercase())
        }

        _countryResult.value = data


    }

}