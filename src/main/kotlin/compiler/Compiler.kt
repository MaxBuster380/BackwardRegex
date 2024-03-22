package org.example.compiler

import org.example.model.RegexSymbol
import org.example.model.SymbolSequence

class Compiler {

    val subSequences = mutableListOf<MutableList<RegexSymbol>>()
    lateinit var state : CompilerState

    fun generate(regex : Regex) : RegexSymbol {
        //println("Compiling regex $regex")
        val res = mutableListOf<RegexSymbol>()

        state = ClassicSequenceState(this)

        subSequences.clear()
        subSequences += res

        for(char in regex.toString()) {
            state.useCharacter(char, subSequences.last())
            //println("$char : $subSequences")
        }

        return SymbolSequence(res)
    }
}