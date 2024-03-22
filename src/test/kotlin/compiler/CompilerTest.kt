package compiler

import org.example.compiler.Compiler
import kotlin.test.Test
import kotlin.test.assertTrue

class CompilerTest {

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
        """[^\w0]""".toRegex()
    )

    @Test
    fun checkAllRegex() {
        val compiler = Compiler()
        for( regex in sampleRegex ) {
            val compiledRegex = compiler.generate( regex )

            for( i in 1..100 ) {
                val generatedText = compiledRegex.generateMatchingText()
                assertTrue(regex.matches(generatedText), "Failed on \"$regex\" with \"$generatedText\"")
            }
        }
    }
}