package org.example.compiler

import org.example.model.*

class CharacterSetBuilderState(
    private val compiler : Compiler,
    private val previousState : CompilerState
) : CompilerState {

    private var symbolsCreated = 0

    private var rangeBuilt = false
    private var rangeFirstChar : Char? = null

    override fun useCharacter(char: Char, symbols: MutableList<RegexSymbol>) {

        if (char == ']') {
            compile(symbols)
            compiler.state = previousState
            return
        }

        if (char == '\\') {
            symbolsCreated += 1
            compiler.state = SpecialCharacterState(compiler, this)
            return
        }

        if (char == '-' && !rangeBuilt && symbols.isNotEmpty() && symbols.last() is StaticCharacter && symbolsCreated > 0) {
            rangeFirstChar = (symbols.removeLast() as StaticCharacter).char
            symbolsCreated -= 1
            rangeBuilt = true
            return
        }

        if (rangeBuilt) {
            symbols += CharacterRange(rangeFirstChar!!, char)
            symbolsCreated += 1
            rangeBuilt = false
            return
        }

        symbols += StaticCharacter(char)
        symbolsCreated += 1
    }

    private fun compile(symbols: MutableList<RegexSymbol>) {

        val list = symbols.subList(symbols.size - symbolsCreated, symbols.size).toList()

        symbols.removeAll(list)

        symbols += OrGroup(list)
    }

}