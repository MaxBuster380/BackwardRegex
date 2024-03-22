package org.example.model

import kotlin.random.Random

internal class OptionalSymbol(
    private val subSymbol : RegexSymbol
) : RegexSymbol {
    override fun generateMatchingText(): String {
        return if (Random.nextBoolean()) {
            subSymbol.generateMatchingText()
        } else {
            ""
        }
    }
    override fun toString(): String {
        return if (subSymbol is StaticCharacter || subSymbol is OrGroup || subSymbol is CharacterClass) {
            "${subSymbol}?"
        } else {
            "(${subSymbol})?"
        }
    }
}