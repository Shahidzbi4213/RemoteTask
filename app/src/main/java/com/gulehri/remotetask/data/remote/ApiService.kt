package com.gulehri.remotetask.data.remote

import com.gulehri.remotetask.models.Countries
import com.gulehri.remotetask.models.CountryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("all?fields=name,flags")
    suspend fun getAllCountries(): Response<Countries>


    @GET("name/{name}?fields=name,unMember,flags,capital,capitalInfo,idd")
    suspend fun getCountryByName(
        @Path("name") name:String = "pakistan"
    ): Response<CountryItem>
}