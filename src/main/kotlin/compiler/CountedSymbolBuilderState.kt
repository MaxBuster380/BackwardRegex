package org.example.compiler

import org.example.model.CountedSymbol
import org.example.model.RegexSymbol
import org.example.model.UnboundedCountedSymbol

class CountedSymbolBuilderState(
    private val compiler: Compiler
) : CompilerState {

    private var minimum : Int? = null
    private var maximum : Int? = null

    private var finishedMinimum = false

    override fun useCharacter(char: Char, symbols: MutableList<RegexSymbol>) {
        if (char == '}') {
            compile(symbols)
            compiler.state = ClassicSequenceState(compiler)
            return
        }

        if (char == ',') {
            finishedMinimum = true
            return
        }

        assert(char.isDigit())
        if (!finishedMinimum) {
            minimum = if (minimum == null) {
                char.digitToInt()
            } else {
                minimum!! * 10 + char.digitToInt()
            }
        } else {
            maximum = if (maximum == null) {
                char.digitToInt()
            } else {
                maximum!! * 10 + char.digitToInt()
            }

        }
    }

    private fun compile(symbols: MutableList<RegexSymbol>) {
        val countedSymbol = symbols.removeLast()

        if (finishedMinimum) {
            if (maximum == null) {
                symbols += UnboundedCountedSymbol(countedSymbol, minimum!!)
            } else {
                symbols += CountedSymbol(countedSymbol, minimum!!, maximum!!)
            }
        } else {
            symbols += CountedSymbol(countedSymbol, minimum!!, minimum!!)
        }
    }
}