package org.example.model

import org.example.compiler.ClassicSequenceState

/**
 * Any sequence of characters that can only be that value.
 */
class StaticCharacter(
    val char : Char
) : RegexSymbol {
    override fun generateMatchingText(): String {
        return char.toString()
    }

    override fun toString(): String {
        return if (char in ClassicSequenceState.RESERVED_CHARACTERS) {
            "\\$char"
        } else {
            char.toString()
        }
    }
}