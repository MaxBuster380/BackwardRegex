package org.example.compiler

import org.example.model.*

class Compiler {

    val subSequences = mutableListOf<MutableList<RegexSymbol>>()
    lateinit var state : CompilerState

    fun generate(regex : String) : RegexSymbol {
        val res = mutableListOf<RegexSymbol>()

        state = ClassicSequenceState(this)

        subSequences.clear()
        subSequences += res

        for(char in regex) {
            state.useCharacter(char, subSequences.last())
        }

        return SymbolSequence(res)
    }
}