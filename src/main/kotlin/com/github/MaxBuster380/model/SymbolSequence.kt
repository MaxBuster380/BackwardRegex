package org.example.model

import com.github.MaxBuster380.compiler.GroupsTallier

internal class SymbolSequence(
    private val symbols : List<RegexSymbol>
) : RegexSymbol {
    override fun generateMatchingText(groups: GroupsTallier): String {
        var res = ""

        for(symbol in symbols) {
            res = "${res}${symbol.generateMatchingText(groups)}"
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