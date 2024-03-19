package org.example.compiler

import org.example.model.*

class ClassicSequenceState(
    private val compiler : Compiler
) : CompilerState {

    companion object {
        val RESERVED_CHARACTERS = "*+?^$[]()\\.{}|".toSet()
    }

    private var orPossibilities = 0

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
                    orPossibilities = 1
                    compiler.subSequences += mutableListOf<RegexSymbol>()
                }

                ')' -> {
                    val lastSubSequences = compiler.subSequences.subList(
                        compiler.subSequences.size - orPossibilities,
                        compiler.subSequences.size
                    ).toList()
                    compiler.subSequences.removeAll(lastSubSequences)

                    compiler.subSequences.last() += OrGroup(lastSubSequences.map{SymbolSequence(it)})
                }

                '|' -> {
                    orPossibilities += 1
                    compiler.subSequences += mutableListOf<RegexSymbol>()
                }

                '{' -> {
                    compiler.state = CountedSymbolBuilderState(compiler)
                }

                '[' -> {
                    compiler.state = CharacterSetBuilderState(compiler, this)
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