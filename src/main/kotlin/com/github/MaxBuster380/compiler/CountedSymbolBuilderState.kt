/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster380
 *
 * This is the "CountedSymbolBuilderState.kt" file from the BackwardRegex project.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.MaxBuster380.compiler

import org.example.model.CountedSymbol
import org.example.model.RegexSymbol
import org.example.model.UnboundedCountedSymbol

internal class CountedSymbolBuilderState(
    private val backwardRegexCompiler: BackwardRegexCompiler,
    private val previousState : CompilerState
) : CompilerState {

    private var minimum : Int? = null
    private var maximum : Int? = null

    private var finishedMinimum = false

    override fun useCharacter(char: Char, symbols: MutableList<RegexSymbol>) {
        if (char == '}') {
            compile(symbols)
            backwardRegexCompiler.state = previousState
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