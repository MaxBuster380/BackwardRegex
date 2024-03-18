package org.example.model

import kotlin.math.abs
import kotlin.random.Random

/**
 * # a+
 */
class AtLeastOneTime(
    private val subSymbol : RegexSymbol
) : RegexSymbol {
    override fun generateMatchingText(): String {
        var res = ""

        val times = 1 + abs(Random.nextInt()) % (RegexSymbol.MAXIMUM_ARBITRARY_SIZE -1)

        for(i in 1..times) {
            res = "${res}${subSymbol.generateMatchingText()}"
        }

        return res
    }

    override fun toString(): String {
        return if (subSymbol is StaticCharacter || subSymbol is OrGroup || subSymbol is CharacterClass) {
            "${subSymbol}+"
        } else {
            "(${subSymbol})+"
        }
    }
}