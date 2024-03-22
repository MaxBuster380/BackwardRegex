package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier
import kotlin.math.abs
import kotlin.random.Random

/**
 * # a*
 */
internal class AnyTimes(
    private val subSymbol : RegexSymbol
) : RegexSymbol {
    override fun generateMatchingText(groups: GroupsTallier): String {
        var res = ""

        val times = abs(Random.nextInt()) % (RegexSymbol.MAXIMUM_ARBITRARY_SIZE)

        for(i in 1..times) {
            res = "${res}${subSymbol.generateMatchingText(groups)}"
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