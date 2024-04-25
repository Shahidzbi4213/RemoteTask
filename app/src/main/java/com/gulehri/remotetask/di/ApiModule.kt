package com.gulehri.remotetask.di

import com.gulehri.remotetask.data.remote.ApiConstants
import com.gulehri.remotetask.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Singleton
    @Provides
    fun provideBaseUrl() = ApiConstants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder().readTimeout(
                    15, TimeUnit.SECONDS
                ).callTimeout(
                    15, TimeUnit.SECONDS
                ).writeTimeout(
                    15, TimeUnit.SECONDS
                ).connectTimeout(
                    15, TimeUnit.SECONDS
                ).build()
            ).build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}