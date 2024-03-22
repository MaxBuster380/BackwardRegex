package org.example.compiler

import org.example.model.*

class CharacterSetBuilderState(
    private val compiler : Compiler,
    private val previousState : CompilerState
) : CompilerState {

    private var rangeBuilt = false
    private var rangeFirstChar : Char? = null
    private var inverse = false
    private var symbolsCreated = 0

    override fun useCharacter(char: Char, symbols: MutableList<RegexSymbol>) {

        if (char == ']') {
            compile(symbols)
            compiler.state = previousState
            return
        }

        if (char == '\\') {
            compiler.state = SpecialCharacterState(compiler, this)
            symbolsCreated += 1
            return
        }

        if (char == '-' && !rangeBuilt && symbols.isNotEmpty() && (symbols.last() is StaticCharacter)) {
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

        if (symbolsCreated == 0 && char == '^') {
            inverse = true
            return
        }

        symbols += StaticCharacter(char)
        symbolsCreated += 1
    }

    private fun compile(symbols: MutableList<RegexSymbol>) {

        val list = symbols.subList(symbols.size - symbolsCreated, symbols.size).toList()

        //println("COMPILING SET : $list")

        for (i in list.indices)
            symbols.removeLast()

        val collection = CollectionCharacterSet(list as List<CharacterSet>)

        symbols += if (!inverse) {
            collection
        } else {
            InverseCharacterSet(collection)
        }
    }

}