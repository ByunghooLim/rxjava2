package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.collections.ArrayList

private var destSum = 0
private var count = 0

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val inputList = ArrayList<Int>()

    while (input.hasNext()) {
        inputList.add(input.nextInt())
    }

    inputList.removeAt(0)

//    inputList.add(4)
//    inputList.add(7)
//    inputList.add(10)

    inputList.forEach {
        destSum = it
        count = 0

        sum(1, 0)
        sum(2, 0)
        sum(3, 0)

        println(count)
    }

}

private fun sum(num: Int, sum: Int) {
    val newSum = sum + num

    if (newSum == destSum) {
        count++
        return
    } else if (sum > destSum)
        return

    sum(1, newSum)
    sum(2, newSum)
    sum(3, newSum)
}