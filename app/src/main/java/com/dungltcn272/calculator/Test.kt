package com.dungltcn272.calculator

import java.util.LinkedList

fun main(){
    val chain = "-630.6667-6"

    val splitChain =
        LinkedList(chain.split(Regex("(?<=[-+x/])|(?=[-+x/])"))).filter { it.isNotEmpty() }
            .toCollection(LinkedList())

    var i = 0
    while (i < splitChain.size - 1) {

        if (splitChain[i] == "-" && splitChain[i + 1].toDoubleOrNull() !=null) {
            splitChain[i + 1] = "-${splitChain[i + 1]}"
            splitChain.removeAt(i)
            if (i != 0 && splitChain[i - 1].toDoubleOrNull() != null){
                splitChain.add(i, "+")
            }
        }
        else{
            i++
        }
    }
    println(splitChain)

}

fun calculate(chain: String) : String{
    val splitChain =
        LinkedList(chain.split(Regex("(?<=[-+*/])|(?=[-+*/])"))).filter { it.isNotEmpty() }
            .toCollection(LinkedList())
    if (splitChain[0] == "-") {
        splitChain[1] = "-${splitChain[1]}"
        splitChain.removeAt(0)
    }

    while (splitChain.size > 1) {
        if (splitChain.contains("*") || splitChain.contains("/")) {
            val index = splitChain.indexOfFirst { it == "*" || it == "/" }
            val result = when (splitChain[index]) {
                "*" -> splitChain[index - 1].toDouble() * splitChain[index + 1].toDouble()
                "/" -> splitChain[index - 1].toDouble() / splitChain[index + 1].toDouble()
                else -> 0.0
            }
            splitChain[index - 1] = result.toString()
            splitChain.removeAt(index)
            splitChain.removeAt(index)
        } else {
            val result = when (splitChain[1]) {
                "+" -> splitChain[0].toDouble() + splitChain[2].toDouble()
                "-" -> splitChain[0].toDouble() - splitChain[2].toDouble()
                else -> 0.0
            }
            splitChain[0] = result.toString()
            splitChain.removeAt(1)
            splitChain.removeAt(1)
        }
    }
    val finalResult = splitChain[0].toDouble()

    return if (finalResult == finalResult.toInt().toDouble()) {
        finalResult.toInt().toString()
    } else {
        finalResult.toString()
    }
}

