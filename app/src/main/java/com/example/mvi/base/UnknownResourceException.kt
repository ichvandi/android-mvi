package com.example.mvi.base

class UnknownResourceException : Exception() {
    override val message: String
        get() = "Resource type not found!"
}