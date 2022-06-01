package com.example.mvi.util.epoxy

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector
import com.airbnb.epoxy.ModelView


fun ModelCollector.gridBuilder(builder: EpoxyGridBuilder.() -> Unit): VerticalGridCarouselModel_ {
    val verticalGridBuilder = EpoxyGridBuilder().apply { builder() }
    add(verticalGridBuilder.verticalGridModel)
    return verticalGridBuilder.verticalGridModel
}

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class VerticalGridCarousel(context: Context) : Carousel(context) {

    init {
        setBackgroundColor(Color.YELLOW)
    }

    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }
}

class EpoxyGridBuilder(
    internal val verticalGridModel: VerticalGridCarouselModel_ = VerticalGridCarouselModel_()
) : ModelCollector, VerticalGridCarouselModelBuilder by verticalGridModel {
    private val models = mutableListOf<EpoxyModel<*>>()

    override fun add(model: EpoxyModel<*>) {
        models.add(model)

        // Set models list every time a model is added so that it can run debug validations to
        // ensure it is still valid to mutate the carousel model.
        verticalGridModel.models(models)
    }
}
