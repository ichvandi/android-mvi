package com.example.mvi.util

import com.example.mvi.base.AppDispatcher
import com.example.mvi.base.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <ResultType, RequestType> networkBoundResource(
    dispatcher: AppDispatcher,
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> Flow<Resource<RequestType>>,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: suspend (ResultType) -> Boolean
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading)

    // Listen data from database
    // This flow will called if database got updated
    // Add `distinctUntilChanged()` before collect
    // so flow isn't collecting when old data == new data
    query().collect { data ->

        // If data is not null or empty, dispatch it
        if (data != null) {
            if (data is ArrayList<*> && data.isNotEmpty()) {
                emit(Resource.Success(data))
            }

            if (data !is ArrayList<*>) {
                emit(Resource.Success(data))
            }
        }

        // Should we fetch data from network
        if (shouldFetch(data)) {
            fetch().collect { response ->
                when (response) {
                    is Resource.Success -> response.data?.let { saveFetchResult(it) }
                    is Resource.Error.BadRequest -> emit(response)
                    is Resource.Error.ConnectionError -> emit(response)
                    is Resource.Error.ConnectionTimeout -> emit(response)
                    is Resource.Error.ServerError -> emit(response)
                    is Resource.Error.Unknown -> emit(response)
                    Resource.Loading -> emit(Resource.Loading)
                }
            }
        }
    }
}.flowOn(dispatcher.io)