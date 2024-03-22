/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster380
 *
 * This is the "OrGroup.kt" file from the BackwardRegex project.
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

package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier
import kotlin.math.abs
import kotlin.random.Random

/**
 * # (a|b|c)
 */
internal class OrGroup(
    private val symbols : List<RegexSymbol>
) : RegexSymbol {
    override fun generateMatchingText(groups: GroupsTallier): String? {
        val index = abs(Random.nextInt()) % (symbols.size)

        val groupIndex = groups.newGroup()

        val res = symbols[index].generateMatchingText(groups)

        groups[groupIndex] = res ?: ""

        return res
    }

    override fun toString(): String {
        var res = "("

        if (symbols.isNotEmpty()) {
            res += symbols[0].toString()

            for(i in 1..<symbols.size) {
                res = "${res}|${symbols[i]}"
            }
        }
        res = "${res})"

        return res
    }
}