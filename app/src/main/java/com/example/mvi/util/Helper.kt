package com.example.mvi.util

import com.example.mvi.BuildConfig
import com.example.mvi.base.AppDispatcher
import com.example.mvi.base.ErrorResponse
import com.example.mvi.base.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

inline fun <reified T : Any> networkCall(crossinline block: suspend () -> Response<T>): Flow<Resource<T>> {
    return flow {
        try {
            emit(Resource.Loading)

            val response = block()
            when (response.code()) {
                in 200..226,
                in 300..308 -> {
                    emit(Resource.Success(response.body()))
                }
                in 400..451 -> {
                    val error = response.getError()
                    Timber.e(error.toString())
                    emit(Resource.Error.BadRequest(error.message))
                }
                in 500..511 -> {
                    val error = response.getError()
                    Timber.e(error.toString())

                    if (BuildConfig.DEBUG) {
                        emit(Resource.Error.ServerError(error.message))
                    } else {
                        emit(Resource.Error.ServerError("Sorry, there was an error on the server"))
                    }
                }
                else -> throw IllegalArgumentException("Unknown HTTP Status Code: ${response.code()}")
            }
        } catch (e: SocketTimeoutException) {
            Timber.e(e)
            emit(Resource.Error.ConnectionTimeout("The connection has timed out"))
        } catch (e: Exception) {
            when (e) {
                is ConnectException, is UnknownHostException -> {
                    Timber.e(e)
                    emit(Resource.Error.ConnectionError("Unable to connect to server, please check your internet connection"))
                }
                else -> throw e
            }
        } catch (e: Exception) {
            Timber.e(e)
            emit(Resource.Error.Unknown("Sorry, an unexpected error occurred"))
        }
    }
}

fun Response<*>.getError(): ErrorResponse {
    return try {
        Gson().fromJson(errorBody()?.charStream()?.readText(), ErrorResponse::class.java)
    } catch (e: Exception) {
        Timber.e(e)
        ErrorResponse(-1, "Unknown Error: Can't parse error message")
    }
}

inline fun <T, R> Flow<Resource<T>>.mapResource(
    dispatcher: AppDispatcher,
    crossinline mapper: (value: T?) -> R
): Flow<Resource<R>> =
    transform { resource ->
        return@transform when (resource) {
            is Resource.Success -> emit(Resource.Success(mapper(resource.data)))
            is Resource.Error.BadRequest -> emit(resource)
            is Resource.Error.ConnectionError -> emit(resource)
            is Resource.Error.ConnectionTimeout -> emit(resource)
            is Resource.Error.ServerError -> emit(resource)
            is Resource.Error.Unknown -> emit(resource)
            Resource.Loading -> emit(Resource.Loading)
        }
    }.flowOn(dispatcher.default)
