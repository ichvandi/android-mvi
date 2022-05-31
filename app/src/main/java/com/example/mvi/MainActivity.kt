package com.example.mvi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.mvi.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.bind(viewModel.state, viewModel.event, viewModel::setAction)
    }

    private fun ActivityMainBinding.bind(
        state: Flow<MainState>,
        event: Flow<MainEvent>,
        action: (MainAction) -> Unit
    ) {
        bindState(state)
        bindEvent(event)

        btnA.setOnClickListener {
            action(MainAction.FirstAction("admin"))
        }

        btnB.setOnClickListener {
            action(MainAction.FirstAction("user"))
        }

        btnC.setOnClickListener {
            action(MainAction.SecondAction)
        }
    }

    private fun ActivityMainBinding.bindState(state: Flow<MainState>) = lifecycleScope.launch {
        state.flowWithLifecycle(lifecycle).collect { state ->
            when (state) {
                is MainState.FirstState -> {
                    tvResult.text = state.text
                }
                MainState.SecondState -> {
                    tvResult.text = "Hello World!"
                }
            }
        }
    }

    private fun ActivityMainBinding.bindEvent(event: Flow<MainEvent>) = lifecycleScope.launch {
        event.flowWithLifecycle(lifecycle).collect { event ->
            when (event) {
                MainEvent.FirstEvent -> {
                    Toast.makeText(applicationContext, "First Event", Toast.LENGTH_SHORT).show()
                }
                MainEvent.SecondEvent -> {
                    Toast.makeText(applicationContext, "Second Event", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}