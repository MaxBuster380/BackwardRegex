package org.example.compiler

import org.example.model.*

class ClassicSequenceState(
    private val compiler : Compiler
) : CompilerState {

    companion object {
        val RESERVED_CHARACTERS = "*+?^$[]()\\.{}".toSet()
    }

    override fun useCharacter(char: Char, symbols: MutableList<RegexSymbol>) {

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
                    compiler.subSequences += mutableListOf<RegexSymbol>()
                }

                ')' -> {
                    val lastSubSequence = compiler.subSequences.removeLast()
                    compiler.subSequences.last() += SymbolSequence(lastSubSequence)
                }

                '{' -> {
                    compiler.state = CountedSymbolBuilderState(compiler)
                }

                '[' -> {
                    compiler.state = CharacterSetBuilderState(compiler)
                }

                '\\' -> {
                    compiler.state = SpecialCharacterState(compiler, this)
                }

                '.' -> {
                    compiler.subSequences.last() += CharacterClass.ANY
                }

                else -> {}
            }
        } else {
            symbols += StaticCharacter(char)
        }
    }
}