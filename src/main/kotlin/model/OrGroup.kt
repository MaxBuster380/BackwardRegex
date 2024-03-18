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
}