package org.example.model

internal interface CharacterSet : RegexSymbol {
    fun contains(char: Char): Boolean
}