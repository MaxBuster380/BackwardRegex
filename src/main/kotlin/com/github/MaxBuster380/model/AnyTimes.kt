package org.example.model

import kotlin.math.abs
import kotlin.random.Random

/**
 * # a*
 */
internal class AnyTimes(
    private val subSymbol : RegexSymbol
) : RegexSymbol {
    override fun generateMatchingText(): String {
        var res = ""

        val times = abs(Random.nextInt()) % (RegexSymbol.MAXIMUM_ARBITRARY_SIZE)

        for(i in 1..times) {
            res = "${res}${subSymbol.generateMatchingText()}"
        }

        return res
    }

    override fun toString(): String {
        return if (subSymbol is StaticCharacter || subSymbol is OrGroup || subSymbol is CharacterClass) {
            "${subSymbol}*"
        } else {
            "(${subSymbol})*"
        }
    }
}