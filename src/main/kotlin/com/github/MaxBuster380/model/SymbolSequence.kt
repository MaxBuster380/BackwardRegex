package org.example.model

internal class SymbolSequence(
    private val symbols : List<RegexSymbol>
) : RegexSymbol {
    override fun generateMatchingText(): String {
        var res = ""

        for(symbol in symbols) {
            res = "${res}${symbol.generateMatchingText()}"
        }

        return res
    }

    override fun toString(): String {
        var res = ""

        if (symbols.isNotEmpty()) {
            for(element in symbols) {
                res = "${res}${element}"
            }
        }

        return res
    }
}