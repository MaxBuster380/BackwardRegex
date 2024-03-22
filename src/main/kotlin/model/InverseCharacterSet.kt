package org.example.model

import kotlin.random.Random

class InverseCharacterSet(
    private val subSet: CharacterSet
) : CharacterSet {
    override fun generateMatchingText(): String {
        var res: Char
        do {
            res = randomChar()
        } while (subSet.contains(res))
        return res.toString()
    }

    override fun contains(char: Char): Boolean {
        return !subSet.contains(char)
    }

    override fun toString(): String {
        return super.toString()
    }

    private fun randomChar(): Char = Char(Random.nextInt().toUShort())

}