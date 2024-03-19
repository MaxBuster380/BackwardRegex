package org.example

import org.example.compiler.Compiler

fun main() {

    //val passwordRegex = """(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}""".toRegex()

    val shortDoubleRegex = """^(-?\d+\.\d{1,3})\d*$""".toRegex()

    val compiler = Compiler()
    val compiledRegex = compiler.generate( shortDoubleRegex )

    println( compiledRegex )

    for(i in 1..10)
        println(">\t" + compiledRegex.generateMatchingText() )
}