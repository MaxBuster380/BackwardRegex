/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster380
 *
 * This is the "ClassicSequenceState.kt" file from the BackwardRegex project.
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

package org.example.compiler

import com.github.MaxBuster380.compiler.CompilerState
import org.example.model.*

internal class ClassicSequenceState(
    private val compiler: BackwardRegexCompiler,
    private val previousState: CompilerState?
) : CompilerState {

    companion object {
        val RESERVED_CHARACTERS = "*+?^$[]()\\.{}|-".toSet()
    }

    private var orPossibilities = 1

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
                    compiler.subSequences += mutableListOf<RegexSymbol>()
                    compiler.state = ClassicSequenceState(compiler, this)
                }

                ')' -> {
                    val size = compiler.subSequences.size

                    val lastSubSequences = compiler.subSequences.subList(
                        size - orPossibilities, size
                    ).toList()

                    for (i in lastSubSequences.indices)
                        compiler.subSequences.removeLast()

                    compiler.subSequences.last() += OrGroup(lastSubSequences.map{SymbolSequence(it)})
                    compiler.state = previousState!!
                }

                '|' -> {
                    orPossibilities += 1
                    compiler.subSequences += mutableListOf<RegexSymbol>()
                }

                '{' -> {
                    compiler.state = CountedSymbolBuilderState(compiler, this)
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

                '-' -> {
                    symbols += StaticCharacter('-')
                }

                else -> {}
            }
        } else {
            symbols += StaticCharacter(char)
        }
    }
}