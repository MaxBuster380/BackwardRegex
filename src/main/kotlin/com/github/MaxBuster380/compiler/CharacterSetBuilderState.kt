/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster380
 *
 * This is the "CharacterSetBuilderState.kt" file from the BackwardRegex project.
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

import org.example.model.*

internal class CharacterSetBuilderState(
    private val compiler: BackwardRegexCompiler,
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

        if (rangeBuilt) {
            symbols += StaticCharacter(rangeFirstChar!!)
            symbols += StaticCharacter('-')
            symbolsCreated += 2
        }


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