package org.example

import org.example.model.*

class Compiler {

    companion object {
        val RESERVED_CHARACTERS = "*+?^$[]()".toSet()
    }

    private val subSequences = mutableListOf<MutableList<RegexSymbol>>()

    fun generate(regex : String) : RegexSymbol {
        val res = mutableListOf<RegexSymbol>()
        subSequences.clear()
        subSequences += res

        for(char in regex) {
            useCharacter(char, subSequences.last())
        }

        return SymbolSequence(res)
    }

    private fun useCharacter(char : Char, symbols : MutableList<RegexSymbol>) {

        if (char in RESERVED_CHARACTERS) {
            when (char) {
                '*' -> {
                    val previousSymbol = symbols.removeLast()
                    symbols += AnyTimes(previousSymbol)
                }
                '+' -> {
                    val previousSymbol = symbols.removeLast()
                    symbols += AtLeastOneTime(previousSymbol)
                }
                '?' -> {
                    val previousSymbol = symbols.removeLast()
                    symbols += OptionalSymbol(previousSymbol)
                }
                '(' -> {
                    subSequences += mutableListOf<RegexSymbol>()
                }
                ')' -> {
                    val lastSubSequence = subSequences.removeLast()
                    subSequences.last() += SymbolSequence(lastSubSequence)
                }
                else -> {}
            }
        } else {
            symbols += StaticCharacter(char)
        }
    }
}