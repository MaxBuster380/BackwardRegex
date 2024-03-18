package org.example.model

import kotlin.math.abs
import kotlin.random.Random

/**
 * # \[abc]
 */
class CharacterRandomSet(
    val characters : Set<Char>
) : CharacterSet {
    override fun generateMatchingText(): String {
        val randomIndex = abs(Random.nextInt()) % (characters.size)
        return characters.toList()[randomIndex].toString()
    }

    override fun toString(): String = characters.toString()
}