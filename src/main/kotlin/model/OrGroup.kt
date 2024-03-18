package org.example.model

import kotlin.math.abs
import kotlin.random.Random

/**
 * # (a|b|c)
 */
class OrGroup(
    private val symbols : List<RegexSymbol>
) : RegexSymbol {
    override fun generateMatchingText(): String {
        val index = abs(Random.nextInt()) % (symbols.size)
        return symbols[index].generateMatchingText()
    }

    override fun toString(): String {
        var res = "("

        if (symbols.isNotEmpty()) {
            res += symbols[0].toString()

            for(i in 1..<symbols.size) {
                res = "${res}|${symbols[i]}"
            }
        }
        res = "${res})"

        return res
    }
}