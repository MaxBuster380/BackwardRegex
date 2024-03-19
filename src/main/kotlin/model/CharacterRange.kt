package org.example.model

import kotlin.math.abs
import kotlin.random.Random

class CharacterRange(
    private val first : Char,
    private val second : Char
) : CharacterSet {

    override fun generateMatchingText(): String {

        val firstCode = first.code
        val secondCode = second.code

        if (secondCode < firstCode) return ""

        val generatedCode = abs(Random.nextInt()) % (secondCode - firstCode + 1) + firstCode

        return generatedCode.toChar().toString()
    }

    override fun toString(): String {
        return "[${first}-${second}]"
    }
}