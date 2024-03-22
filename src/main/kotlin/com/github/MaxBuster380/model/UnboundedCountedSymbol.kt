package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier
import kotlin.math.abs
import kotlin.random.Random

internal class UnboundedCountedSymbol(
    private val subSymbol: RegexSymbol,
    private val minimum : Int
) : RegexSymbol {
    override fun generateMatchingText(groups: GroupsTallier): String {
        var res = ""

        val times = abs(Random.nextInt()) % (RegexSymbol.MAXIMUM_ARBITRARY_SIZE - minimum) + minimum

        for(i in 1..times) {
            res = "${res}${subSymbol.generateMatchingText(groups)}"
        }

        return res
    }

    override fun toString(): String {
        val range = "{$minimum,}"

        return if (subSymbol is StaticCharacter || subSymbol is OrGroup || subSymbol is CharacterClass) {
            "${subSymbol}$range"
        } else {
            "(${subSymbol})$range"
        }
    }
}