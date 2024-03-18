package org.example.compiler

import org.example.model.RegexSymbol

interface CompilerState {
    fun useCharacter(char : Char, symbols : MutableList<RegexSymbol>)
}