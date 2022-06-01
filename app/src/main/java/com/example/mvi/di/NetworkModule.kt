package com.example.mvi.di

import com.example.mvi.data.remote.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Ichvandi
 * Created on 31/05/2022 at 22:50.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun addQueryParam(it: Interceptor.Chain): Response {
        var request = it.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("api_key", "55a2f1c40045643e474700423fa345df")
            .build()
        request = request
            .newBuilder()
            .url(url)
            .build()
        return it.proceed(request)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(::addQueryParam)
            .build()
    }

    @Provides
    fun providesRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

}