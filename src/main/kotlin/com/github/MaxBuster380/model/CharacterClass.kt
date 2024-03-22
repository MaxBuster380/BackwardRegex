package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier
import kotlin.math.abs
import kotlin.random.Random

internal enum class CharacterClass : CharacterSet {

    /**
     * # \s
     */
    WHITE_SPACE {
        override fun generateMatchingText(groups: GroupsTallier): String {
            val randomIndex = abs(Random.nextInt()) % (WHITE_ALPHABET.size)
            return WHITE_ALPHABET.toList()[randomIndex].toString()
        }

        override fun contains(char: Char): Boolean = WHITE_ALPHABET.contains(char)

        override fun toString(): String = "\\s"
    },

    /**
     * # \S
     */
    NOT_WHITE_SPACE {
        override fun generateMatchingText(groups: GroupsTallier): String {
            var res : Char
            do {
                res = randomChar()
            } while (res.isWhitespace())
            return res.toString()
        }
        override fun contains(char: Char): Boolean = !WHITE_ALPHABET.contains(char)
        override fun toString(): String = "\\S"
    },

    /**
     * # \d
     */
    DIGIT {
        override fun generateMatchingText(groups: GroupsTallier): String {
            val randomDigit = abs(Random.nextInt()) % 10
            return randomDigit.toString()
        }
        override fun contains(char: Char): Boolean = char.isDigit()
        override fun toString(): String = "\\d"
    },

    /**
     * # \D
     */
    NOT_DIGIT {
        override fun generateMatchingText(groups: GroupsTallier): String {
            var res : Char
            do {
                res = randomChar()
            } while (res.isDigit())
            return res.toString()
        }
        override fun contains(char: Char): Boolean = !char.isDigit()
        override fun toString(): String = "\\D"
    },

    /**
     * # \w
     */
    WORD {
        override fun generateMatchingText(groups: GroupsTallier): String {
            val randomIndex = abs(Random.nextInt()) % (WORD_ALPHABET.size)
            return WORD_ALPHABET.toList()[randomIndex].toString()
        }
        override fun contains(char: Char): Boolean = WORD_ALPHABET.contains(char)
        override fun toString(): String = "\\w"
    },

    /**
     * # \W
     */
    NOT_WORD {
        override fun generateMatchingText(groups: GroupsTallier): String {
            var res : Char
            do {
                res = randomChar()
            } while (WORD_ALPHABET.contains(res))
            return res.toString()
        }
        override fun contains(char: Char): Boolean = !WORD_ALPHABET.contains(char)
        override fun toString(): String = "\\W"
    },

    /**
     * # .
     */
    ANY {
        override fun generateMatchingText(groups: GroupsTallier): String {
            return randomChar().toString()
        }

        override fun contains(char: Char): Boolean = true
        override fun toString(): String = "\\."
    };

    companion object {
        private val WHITE_ALPHABET = " \n\r\t".toSet()
        private val WORD_ALPHABET = "AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbn_".toSet()

        private fun randomChar() : Char = Char(Random.nextInt().toUShort())
    }
}