package com.example.mvi.model.epoxy

import android.view.View
import coil.load
import com.example.mvi.R
import com.example.mvi.databinding.ItemMovieBinding
import com.example.mvi.util.epoxy.ViewBindingKotlinModel

data class ItemMovieModel(
    private val title: String,
    private val poster: String,
    private val onClickListener: View.OnClickListener
) : ViewBindingKotlinModel<ItemMovieBinding>(R.layout.item_movie) {

    override fun ItemMovieBinding.bind() {
        root.setOnClickListener(onClickListener)

        tvTitle.text = title
        ivPoster.load("https://image.tmdb.org/t/p/w500$poster")
        // https://image.tmdb.org/t/p/w500/jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg
    }

}