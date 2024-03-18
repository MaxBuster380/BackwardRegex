package org.example.model

import kotlin.math.abs
import kotlin.random.Random

enum class CharacterClass : RegexSymbol {

    /**
     * # \s
     */
    WHITE_SPACE {
        override fun generateMatchingText(): String {
            val randomIndex = abs(Random.nextInt()) % (WHITE_ALPHABET.size)
            return WHITE_ALPHABET.toList()[randomIndex].toString()
        }
    },

    /**
     * # \S
     */
    NOT_WHITE_SPACE {
        override fun generateMatchingText(): String {
            var res : Char
            do {
                res = randomChar()
            } while (res.isWhitespace())
            return res.toString()
        }
    },

    /**
     * # \d
     */
    DIGIT {
        override fun generateMatchingText(): String {
            val randomDigit = abs(Random.nextInt()) % 10
            return randomDigit.toString()
        }
    },

    /**
     * # \D
     */
    NOT_DIGIT {
        override fun generateMatchingText(): String {
            var res : Char
            do {
                res = randomChar()
            } while (res.isDigit())
            return res.toString()
        }
    },

    /**
     * # \w
     */
    WORD {
        override fun generateMatchingText(): String {
            val randomIndex = abs(Random.nextInt()) % (WORD_ALPHABET.size)
            return WORD_ALPHABET.toList()[randomIndex].toString()
        }
    },

    /**
     * # \W
     */
    NOT_WORD {
        override fun generateMatchingText(): String {
            var res : Char
            do {
                res = randomChar()
            } while (WORD_ALPHABET.contains(res))
            return res.toString()
        }
    },

    /**
     * # .
     */
    ANY {
        override fun generateMatchingText(): String {
            return randomChar().toString()
        }
    };

    companion object {
        private val WHITE_ALPHABET = " \n\r\t".toSet()
        private val WORD_ALPHABET = "AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbn_".toSet()

        private fun randomChar() : Char = Char(Random.nextInt().toUShort())
    }
}