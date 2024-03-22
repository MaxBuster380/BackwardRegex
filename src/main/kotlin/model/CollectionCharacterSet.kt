package org.example.model

import kotlin.math.abs
import kotlin.random.Random

class CollectionCharacterSet(
    private val subSets: List<CharacterSet>
) : CharacterSet {
    override fun generateMatchingText(): String {
        val randomIndex = abs(Random.nextInt()) % (subSets.size)

        return subSets[randomIndex].generateMatchingText()
    }

    override fun contains(char: Char): Boolean {
        for (subSet in subSets) {
            if (subSet.contains(char)) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        return super.toString()
    }
}