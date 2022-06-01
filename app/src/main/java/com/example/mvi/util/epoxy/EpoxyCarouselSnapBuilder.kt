package com.example.mvi.util.epoxy

import com.airbnb.epoxy.CarouselModelBuilder
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelCollector

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
fun ModelCollector.carouselSnapBuilder(builder: EpoxyCarouselSnapBuilder.() -> Unit): CarouselModel_ {
    val carouselBuilder = EpoxyCarouselSnapBuilder().apply { builder() }
    add(carouselBuilder.carouselSnapModel)
    return carouselBuilder.carouselSnapModel
}

class EpoxyCarouselSnapBuilder(
    internal val carouselSnapModel: CarouselModel_ = CarouselModel_()
) : ModelCollector, CarouselModelBuilder by carouselSnapModel {
    private val models = mutableListOf<EpoxyModel<*>>()

    override fun add(model: EpoxyModel<*>) {
        models.add(model)

        // Set models list every time a model is added so that it can run debug validations to
        // ensure it is still valid to mutate the carousel model.
        carouselSnapModel.models(models)
    }
}
