package com.example.hoo.rxjava.baekjoon

import java.util.*

//private lateinit var result: Array<Int>
private var length = 0
private var resultCount = 0

fun main(args: Array<String>) {
//    val source = ArrayList<Int>()
//    val sc = Scanner(System.`in`)
//
//    while (sc.hasNextInt()) {
//        source.add(sc.nextInt())
//    }

    length = 90

    when (length) {
        1, 2 -> resultCount = 1
        else ->  {
//            result = Array(length) { 0 }
//
//            result[0] = 1
//            result[1] = 0

            getFriendlyBinary(2, 0)
        }
    }

    print(resultCount)
}

private fun getFriendlyBinary(index: Int , value : Int) : Int{
    if (index == length) {
        return 1
    }

    if (value == 0) {
        resultCount += getFriendlyBinary(index + 1, 1)
    }

    resultCount += getFriendlyBinary(index + 1, 0)

    return resultCount
}