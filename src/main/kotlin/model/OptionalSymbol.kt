package org.example.model

import kotlin.random.Random

class OptionalSymbol(
    private val subSymbol : RegexSymbol
) : RegexSymbol {
    override fun generateMatchingText(): String {
        return if (Random.nextBoolean()) {
            subSymbol.generateMatchingText()
        } else {
            ""
        }
    }
}