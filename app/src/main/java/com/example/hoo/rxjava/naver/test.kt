package com.example.hoo.rxjava.naver


fun main(args: Array<String>) {
    println(solution(6666666))
}

fun solution(N: Int): Int {
    return when {
        N >= 0 -> maxValue(N, true)
        else ->  -1 * maxValue(-1 * N, false)
    }
}

private fun maxValue(N : Int, findMax : Boolean) : Int {
    val digitArray = ArrayList<Int>()

    N.toString().forEach {
        digitArray.add(it.toInt() - '0'.toInt())
    }

    var idxOfFive = digitArray.size

    for(i in 0 until digitArray.size) {
        val digit = digitArray[i]

        if(findMax && digit < 5) {
            idxOfFive = i
            break
        }


        if(!findMax && digit > 5) {
            idxOfFive = i
            break
        }
    }

    digitArray.add(idxOfFive, 5)

    return digitArrToDecimal(digitArray)
}

private fun digitArrToDecimal(input : ArrayList<Int>) : Int {
    var decimal = 0

    input.forEach {
        decimal = decimal * 10 + it
    }

    return decimal
}