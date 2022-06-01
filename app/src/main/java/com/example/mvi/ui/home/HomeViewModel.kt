package com.example.mvi.ui.home

import androidx.lifecycle.viewModelScope
import com.example.mvi.base.BaseViewModel
import com.example.mvi.base.Resource
import com.example.mvi.data.repository.contract.IMovieRepository
import com.example.mvi.model.movie.MovieMini
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ichvandi
 * Created on 30/05/2022 at 20:29.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: IMovieRepository
) : BaseViewModel<HomeAction, HomeState, HomeEvent>() {

    override fun handleAction(action: HomeAction) {
        when (action) {
            HomeAction.GetNowPlayingMovies -> getNowPlayingMovies()
            HomeAction.GetUpcomingMovies -> getUpcomingMovies()
        }
    }

    private fun getNowPlayingMovies() = viewModelScope.launch {
        movieRepository.getNowPlayingMovies(1).collect { response ->
            if (response is Resource.Success) {
                setState(HomeState.ShowNowPlayingMovies(response.data.orEmpty()))
            }
        }
    }

    private fun getUpcomingMovies() = viewModelScope.launch {
        movieRepository.getUpcomingMovies(1).collect { response ->
            if (response is Resource.Success) {
                setState(HomeState.ShowUpcomingMovies(response.data.orEmpty()))
            }
        }
    }

}

sealed class HomeAction {
    object GetNowPlayingMovies : HomeAction()
    object GetUpcomingMovies : HomeAction()
}

sealed class HomeState {
    data class ShowNowPlayingMovies(val movies: List<MovieMini>) : HomeState()
    data class ShowUpcomingMovies(val movies: List<MovieMini>) : HomeState()
}

sealed class HomeEvent {
    object FirstEvent : HomeEvent()
    object SecondEvent : HomeEvent()
}
