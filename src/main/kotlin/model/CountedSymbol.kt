package org.example.model

import kotlin.math.abs
import kotlin.random.Random

class CountedSymbol(
    private val subSymbol: RegexSymbol,
    private val minimum : Int,
    private val maximum : Int
) : RegexSymbol {
    override fun generateMatchingText(): String {
        var res = ""

        val times = abs(Random.nextInt()) % (maximum - minimum) + minimum

        for(i in 1..times) {
            res = "${res}${subSymbol.generateMatchingText()}"
        }

        return res
    }

    override fun toString(): String {
        val range = if (minimum != 0) {
            "{$minimum,$maximum}"
        } else {
            "{,$maximum}"
        }

        return if (subSymbol is StaticCharacter || subSymbol is OrGroup || subSymbol is CharacterClass) {
            "${subSymbol}$range"
        } else {
            "(${subSymbol})$range"
        }
    }
}