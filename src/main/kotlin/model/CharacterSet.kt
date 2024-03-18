package org.example.model

import kotlin.math.abs
import kotlin.random.Random

/**
 * # \[abc]
 */
class CharacterSet(
    private val characters : Set<Char>
) : RegexSymbol {
    override fun generateMatchingText(): String {
        val randomIndex = abs(Random.nextInt()) % (characters.size)
        return characters.toList()[randomIndex].toString()
    }
}