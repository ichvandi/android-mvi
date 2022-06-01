package com.example.mvi.base

import com.google.gson.annotations.SerializedName

data class PagingResponse<T>(

    val page: Int,

    val results: List<T>,

    @SerializedName("total_pages")
    val totalPages: Int,

    @SerializedName("total_results")
    val totalResults: Int

)