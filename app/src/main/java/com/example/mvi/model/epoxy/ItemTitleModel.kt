package com.example.mvi.model.epoxy

import com.example.mvi.R
import com.example.mvi.databinding.ItemTitleBinding
import com.example.mvi.util.epoxy.ViewBindingKotlinModel

/**
 * @author Ichvandi
 * Created on 01/06/2022 at 12:24.
 */
data class ItemTitleModel(
    private val title: String
) : ViewBindingKotlinModel<ItemTitleBinding>(R.layout.item_title) {

    override fun ItemTitleBinding.bind() {
        tvTitle.text = title
    }

}
