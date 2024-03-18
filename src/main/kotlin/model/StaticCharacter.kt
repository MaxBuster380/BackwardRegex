package org.example.model

/**
 * Any sequence of characters that can only be that value.
 */
class StaticCharacter(
    private val char : Char
) : RegexSymbol {
    override fun generateMatchingText(): String {
        return char.toString()
    }
}