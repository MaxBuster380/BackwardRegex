package org.example

import org.example.compiler.Compiler

fun main() {
    val emailRegex = "^[\\w\\-]+(\\.[\\w\\-]+)*@([\\w\\-]+\\.)+[\\w\\-]{2,4}\$"

    val helloRegex = "^hel(lo)? w*o+rld?$"

    val anyDigitRegex = "\\d"

    val wordRegex = "[\\w\\-]"

    val numberRegex = "-?[1-9]\\d*(\\.\\d+)?([Ee]-?\\d+)?"

    val compiler = Compiler()
    val compiledRegex = compiler.generate( numberRegex )

    println(compiledRegex)

    for(i in 1..5)
        println(">\t" + compiledRegex.generateMatchingText() )
}