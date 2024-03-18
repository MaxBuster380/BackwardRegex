package org.example

fun main() {
    val regex = "^he(l(lo))?$"

    val compiler = Compiler()
    val compiledRegex = compiler.generate(regex)

    for(i in 1..10)
        println( compiledRegex.generateMatchingText() )
}