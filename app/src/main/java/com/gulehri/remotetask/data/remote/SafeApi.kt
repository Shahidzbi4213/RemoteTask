package com.gulehri.remotetask.data.remote


import com.gulehri.remotetask.data.utils.Utils.getErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException


// Created by Shahid Iqbal on 4/08/2023.
@androidx.annotation.Keep
object SafeApi {

    private var isCanceled: Boolean = false


    // This function makes an API call and returns the result as NetworkResult
    suspend fun <T> apiCall(call: suspend () -> Response<T>): NetworkResult<T> {
        isCanceled = false
        return withContext(Dispatchers.IO) {
            try {
                val response = call.invoke()
                when {

                    response.isSuccessful && response.code() == 200 && response.body() != null && !isCanceled -> {
                        NetworkResult.Success(response.body()!!)
                    }


                    response.code() == 400 -> {
                        NetworkResult.Error(Exception(response.errorBody().getErrorMessage()))
                    }

                    response.code() == 500 -> {
                        NetworkResult.Error(Exception("Failed to Connect"))
                    }

                    else -> {
                        NetworkResult.Error(Exception("Something Went Wrong"))
                    }
                }
            } catch (e: IOException) {
                NetworkResult.Error(Exception(e.message))
            } catch (e: Exception) {
                NetworkResult.Error(e)
            }
        }
    }

    fun cancel() {
        isCanceled = true
    }


}