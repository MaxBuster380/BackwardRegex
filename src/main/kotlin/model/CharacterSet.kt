package org.example.model

interface CharacterSet : RegexSymbol {
    fun contains(char: Char): Boolean
}