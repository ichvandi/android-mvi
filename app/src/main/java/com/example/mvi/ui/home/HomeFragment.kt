package com.example.mvi.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.mvi.R
import com.example.mvi.databinding.FragmentHomeBinding
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel by viewModels<HomeViewModel>()

    private val controller = MovieController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind(viewModel.state, viewModel.event, viewModel::setAction)

        viewModel.setAction(HomeAction.GetNowPlayingMovies)
        viewModel.setAction(HomeAction.GetUpcomingMovies)
    }

    private fun FragmentHomeBinding.bind(
        state: Flow<HomeState>,
        event: Flow<HomeEvent>,
        action: (HomeAction) -> Unit
    ) {
        rvMain.apply {
            adapter = controller.adapter
            setHasFixedSize(false)
        }

        bindState(state, action)
        bindEvent(event, action)
    }

    private fun FragmentHomeBinding.bindState(
        state: Flow<HomeState>,
        action: (HomeAction) -> Unit
    ) = lifecycleScope.launch {
        state.flowWithLifecycle(lifecycle).collect { state ->
            when (state) {
                is HomeState.ShowNowPlayingMovies -> {
                    controller.setNowPlayingMovies(state.movies)
                }
                is HomeState.ShowUpcomingMovies -> {
                    controller.setUpcomingMovies(state.movies)
                }
            }
        }
    }

    private fun FragmentHomeBinding.bindEvent(
        event: Flow<HomeEvent>,
        action: (HomeAction) -> Unit
    ) = lifecycleScope.launch {
        event.flowWithLifecycle(lifecycle).collect { event ->
            when (event) {
                HomeEvent.FirstEvent -> {
                }
                HomeEvent.SecondEvent -> {
                }
            }
        }
    }

}