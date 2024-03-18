package org.example.model

interface RegexSymbol {

    companion object {
        const val MAXIMUM_ARBITRARY_SIZE = 20
    }

    fun generateMatchingText() : String
}