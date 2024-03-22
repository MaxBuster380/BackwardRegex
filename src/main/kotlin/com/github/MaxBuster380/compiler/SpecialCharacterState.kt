/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster380
 *
 * This is the "SpecialCharacterState.kt" file from the BackwardRegex project.
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
import org.example.model.CharacterClass
import org.example.model.RegexSymbol
import org.example.model.StaticCharacter

internal class SpecialCharacterState(
    private val compiler: BackwardRegexCompiler,
    private val previousState : CompilerState
) : CompilerState {
    override fun useCharacter(char: Char, symbols: MutableList<RegexSymbol>) {
        when (char) {
            'n' -> symbols += StaticCharacter('\n')
            't' -> symbols += StaticCharacter('\t')
            'r' -> symbols += StaticCharacter('\r')
            'd' -> symbols += CharacterClass.DIGIT
            'w' -> symbols += CharacterClass.WORD
            's' -> symbols += CharacterClass.WHITE_SPACE
            'D' -> symbols += CharacterClass.NOT_DIGIT
            'W' -> symbols += CharacterClass.NOT_WORD
            'S' -> symbols += CharacterClass.NOT_WHITE_SPACE
            else -> symbols += StaticCharacter(char)
        }
        compiler.state = previousState
    }
}