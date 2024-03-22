/*
 * MIT License
 *
 * Copyright (c) 2024 MaxBuster380
 *
 * This is the "BackwardRegexCompilerTest.kt" file from the BackwardRegex project.
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

package compiler

import com.github.MaxBuster380.compiler.BackwardRegexCompiler
import com.github.MaxBuster380.model.NoMatchSymbol
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class BackwardRegexCompilerTest {

    private val sampleRegex = listOf(
        """^[\w\-]+(\.[\w\-]+)*@([\w\-]+\.)+[\w\-]{2,4}${'$'}""".toRegex(),
        """^hel(lo)? w*o+rld?${'$'}""".toRegex(),
        """\d""".toRegex(),
        """[\w\-]""".toRegex(),
        """-?[1-9]\d*(\.\d+)?([Ee]-?\d+)?""".toRegex(),
        """([048]|\d*[02468][048]|\d*[13579][26])""".toRegex(),
        """^\d{4}-[01]\d-([012]\d|3[01])T([01]\d|2[0-3]):[0-5]\d:[0-5]\d(,\d+)?Z$""".toRegex(),
        """^(-?\d+\.\d{1,3})\d*$""".toRegex(),
        """^\(*\d{3}\)*( |-)*\d{3}( |-)*\d{4}$""".toRegex(),
        """[^\w0]""".toRegex(),
        """^\+?(\d[\d\-. ]+)?(\([\d\-. ]+\))?[\d\-. ]+\d$""".toRegex(),
        """^(0\d{9}|(0\d( (\d\d)){4})|\+\d{11})$""".toRegex(),
        """[0-]""".toRegex(),
        """[-0]""".toRegex(),
        """(a|)""".toRegex(),
        """(a|||)""".toRegex(),
        """(||||a|||)""".toRegex(),
        """(\d\w)\1""".toRegex(),
        """\1(\d\w)""".toRegex()
    )

    @Test
    fun checkAllRegex() {

        val backwardRegexCompiler = BackwardRegexCompiler()
        for (regex in sampleRegex) {
            try {
                val compiledRegex = backwardRegexCompiler.generate(regex)

                if (compiledRegex.regexSymbol is NoMatchSymbol) continue

                for (i in 1..100) {
                    val generatedText = compiledRegex.generateMatchingText()

                    assertNotNull(generatedText)
                    assertTrue(regex.matches(generatedText), "Failed on \"$regex\" with \"$generatedText\"")
                }
            } catch (e: Exception) {
                throw Exception("Failed compiling \"$regex\" with error $e")
            }
        }
    }
}