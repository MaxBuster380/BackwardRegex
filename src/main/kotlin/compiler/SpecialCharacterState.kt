package org.example.compiler

import org.example.model.CharacterClass
import org.example.model.RegexSymbol
import org.example.model.StaticCharacter

class SpecialCharacterState(
    private val compiler: Compiler,
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