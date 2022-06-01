package com.example.mvi.util.epoxy

import android.content.Context
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.ModelView.Size

/**
 * Example that illustrate how to add a builder for nested list (ex: carousel) that allow building
 * it using DSL :
 *
 *   carouselBuilder {
 *     id(...)
 *     for (...) {
 *       carouselItemCustomView {
 *         id(...)
 *       }
 *     }
 *   }
 *
 * @link https://github.com/airbnb/epoxy/issues/847
 */
fun ModelCollector.carouselBuilder(builder: EpoxyCarouselBuilder.() -> Unit): CarouselNoSnapModel_ {
    val carouselBuilder = EpoxyCarouselBuilder().apply { builder() }
    add(carouselBuilder.carouselNoSnapModel)
    return carouselBuilder.carouselNoSnapModel
}

@ModelView(saveViewState = true, autoLayout = Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselNoSnap(context: Context) : Carousel(context) {
    override fun getSnapHelperFactory(): SnapHelperFactory? {
        return null
    }
}

class EpoxyCarouselBuilder(
    internal val carouselNoSnapModel: CarouselNoSnapModel_ = CarouselNoSnapModel_()
) : ModelCollector, CarouselNoSnapModelBuilder by carouselNoSnapModel {
    private val models = mutableListOf<EpoxyModel<*>>()

    override fun add(model: EpoxyModel<*>) {
        models.add(model)

        // Set models list every time a model is added so that it can run debug validations to
        // ensure it is still valid to mutate the carousel model.
        carouselNoSnapModel.models(models)
    }
}
