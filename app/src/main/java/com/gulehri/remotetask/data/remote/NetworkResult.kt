package com.gulehri.remotetask.data.remote


@androidx.annotation.Keep
sealed class NetworkResult<out T> {

    data class Success<out T>(val data: T) : NetworkResult<T>()

    data class Error<out T>(val error: Exception?) : NetworkResult<T>()

    data object Loading : NetworkResult<Nothing>()

    data object Empty : NetworkResult<Nothing>()

    data object Cancel : NetworkResult<Nothing>()


}