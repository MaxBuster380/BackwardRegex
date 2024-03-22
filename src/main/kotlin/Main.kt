package org.example

import org.example.compiler.Compiler

fun main() {

    //val passwordRegex = """(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}""".toRegex()

    val testedRegex = """[^\\]+\\*${'$'}""".toRegex()

    val compiler = Compiler()
    val compiledRegex = compiler.generate(testedRegex)

    println( compiledRegex )

    for(i in 1..10)
        println(">\t" + compiledRegex.generateMatchingText() )
}