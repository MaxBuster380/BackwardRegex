package org.example.model

class SymbolSequence(
    private val symbols : List<RegexSymbol>
) : RegexSymbol {
    override fun generateMatchingText(): String {
        var res = ""

        for(symbol in symbols) {
            res = "${res}${symbol.generateMatchingText()}"
        }

        return res
    }
}