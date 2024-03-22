package com.github.MaxBuster380.compiler

import org.example.model.RegexSymbol

internal interface CompilerState {
    fun useCharacter(char : Char, symbols : MutableList<RegexSymbol>)
}