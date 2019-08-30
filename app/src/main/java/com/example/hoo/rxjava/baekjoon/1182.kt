package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.collections.ArrayList

private lateinit var array: IntArray
private var arraySize = 0
private var toalSum = 0
private var count = 0

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)

    val (first, second) = input.nextLine().split(" ")
    array = input.nextLine().split(" ").map { it.toInt() }.toIntArray()

    arraySize = first.toInt()
    toalSum = second.toInt()

    sum(0, 0 )

    if (toalSum == 0) count -= 1

    print(count)
}

private fun sum(depth: Int, sum: Int) {
    if(depth == arraySize) {
        if (sum == toalSum) {
            count++
        }

        return
    }

    sum(depth + 1, sum + array[depth])
    sum(depth + 1, sum)
}