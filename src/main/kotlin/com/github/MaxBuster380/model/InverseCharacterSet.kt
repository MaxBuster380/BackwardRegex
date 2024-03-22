package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier
import kotlin.random.Random

/**
 * # \[^ABC]
 */
internal class InverseCharacterSet(
    private val subSet: CharacterSet
) : CharacterSet {
    override fun generateMatchingText(groups: GroupsTallier): String {
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