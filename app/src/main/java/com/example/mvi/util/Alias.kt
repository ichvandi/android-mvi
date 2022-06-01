package com.example.mvi.util

import com.example.mvi.base.PagingResponse
import com.example.mvi.base.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * @author Ichvandi
 * Created on 31/05/2022 at 23:17.
 */

typealias ResponsePaging<T> = Response<PagingResponse<T>>

typealias ResourcePagingFlow<T> = Flow<Resource<PagingResponse<T>>>
