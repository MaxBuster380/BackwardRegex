package org.example

import org.example.compiler.Compiler

fun main() {
    val emailRegex = """^[\w\-]+(\.[\w\-]+)*@([\w\-]+\.)+[\w\-]{2,4}${'$'}""".toRegex()

    val helloRegex = """^hel(lo)? w*o+rld?${'$'}""".toRegex()

    val anyDigitRegex = """\d""".toRegex()

    val wordRegex = """[\w\-]""".toRegex()

    val numberRegex = """-?[1-9]\d*(\.\d+)?([Ee]-?\d+)?""".toRegex()

    val multipleOfFourRegex = """([048]|\d*[02468][048]|\d*[13579][26])""".toRegex()

    val passwordRegex = """(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}""".toRegex()

    val isoDateRegex = """^\d{4}-[01]\d-([012]\d|3[01])T([01]\d|2[0-3]):[0-5]\d:[0-5]\d(,\d+)?Z$""".toRegex()

    val compiler = Compiler()
    val compiledRegex = compiler.generate( isoDateRegex )

    println(compiledRegex)

    for(i in 1..10)
        println(">\t" + compiledRegex.generateMatchingText() )
}