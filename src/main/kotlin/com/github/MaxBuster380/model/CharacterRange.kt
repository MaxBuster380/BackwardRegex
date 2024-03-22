package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier
import kotlin.math.abs
import kotlin.random.Random

/**
 * # \[A-Z]
 */
internal class CharacterRange(
    private val first : Char,
    private val second : Char
) : CharacterSet {

    override fun generateMatchingText(groups: GroupsTallier): String {

        val firstCode = first.code
        val secondCode = second.code

        if (secondCode < firstCode) return ""

        val generatedCode = abs(Random.nextInt()) % (secondCode - firstCode + 1) + firstCode

        return generatedCode.toChar().toString()
    }

    override fun contains(char: Char): Boolean {
        return char.code in first.code..second.code
    }

    override fun toString(): String {
        return "[${first}-${second}]"
    }
}