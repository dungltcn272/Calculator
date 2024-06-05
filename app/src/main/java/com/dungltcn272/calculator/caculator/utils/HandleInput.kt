package com.dungltcn272.calculator.caculator.utils

import java.util.LinkedList

object HandleInput {
//    fun isAllowedToAdd(mainString: String, character: Char): Boolean {
//        if (mainString == "0" && character == '0') {
//            return false
//        }
//
//        if (mainString.isEmpty() && (character == 'x' || character == '/')) {
//            return false
//        }
//
//        if (mainString.isNotEmpty() && mainString.last() == '.' && character == '.') {
//            return false
//        }
//
//        if (mainString.isNotEmpty() && mainString.contains(".") && mainString.substring(mainString.lastIndexOf('.')).toDoubleOrNull() != null && character == '.') {
//            return false
//        }
//
//        if(mainString.isNotEmpty() && isOperator(mainString.last()) && character == '.'){
//            return false
//        }
//
//        if (mainString.isNotEmpty() && mainString.last() == '%' && !isOperator(character)) {
//            return false
//        }
//
//        if (mainString.isNotEmpty() && (mainString.last() == 'x' || mainString.last() == '/') && (character == 'x' || character == '/' || character == '+')) {
//            return false
//        }
//        if (mainString.isNotEmpty() && (mainString.last() == '+' || mainString.last() == '-') && (character == '+' || character == '-')) {
//            return false
//        }
//        return true
//    }

    fun isAllowedToAdd(mainString: String, character: Char): Boolean {
        val lastChar = mainString.lastOrNull()
        return when {
            mainString == "0" && character == '0' -> false
            mainString.isEmpty() && character in listOf('x', '/') -> false
            lastChar == '.' && character == '.' -> false
            lastChar in listOf('x', '/', '+', '-') && character == '.' -> false
            mainString.contains(".") && mainString.substringAfterLast('.').toDoubleOrNull() != null && character == '.' -> false
            lastChar == '%' && !isOperator(character) -> false
            lastChar in listOf('x', '/') && character in listOf('x', '/', '+') -> false
            lastChar in listOf('+', '-') && character in listOf('+', '-') -> false
            else -> true
        }
    }

    fun calculate(chain: String): String {
        val splitChain =
            LinkedList(chain.split(Regex("(?<=[-+x/])|(?=[-+x/])"))).filter { it.isNotEmpty() }
                .toCollection(LinkedList())

        var i = 0
        while (i < splitChain.size - 1) {

            if (splitChain[i] == "-" && splitChain[i + 1].toDoubleOrNull() != null) {
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

        if (splitChain.size == 1) {
            return "@@"
        }


        while (splitChain.size > 1) {
            if (splitChain.contains("x") || splitChain.contains("/")) {
                val index = splitChain.indexOfFirst { it == "x" || it == "/" }
                val result = when (splitChain[index]) {
                    "x" -> splitChain[index - 1].toFloat() * splitChain[index + 1].toFloat()
                    "/" -> splitChain[index - 1].toFloat() / splitChain[index + 1].toFloat()
                    else -> 0.0
                }
                splitChain[index - 1] = result.toString()
                splitChain.removeAt(index)
                splitChain.removeAt(index)
            } else {
                val result = when (splitChain[1]) {
                    "+" -> splitChain[0].toFloat() + splitChain[2].toFloat()
                    "-" -> splitChain[0].toFloat() - splitChain[2].toFloat()
                    else -> 0.0
                }
                splitChain[0] = result.toString()
                splitChain.removeAt(1)
                splitChain.removeAt(1)
            }
        }
        val finalResult = splitChain[0].toFloat()

        return if (finalResult == finalResult.toLong().toFloat()) {
            finalResult.toLong().toString()
        } else {
            finalResult.toString()
        }
    }

    private fun isOperator(mainChar: Char): Boolean {
        return mainChar in listOf('+', '-', 'x', '/')
    }
}