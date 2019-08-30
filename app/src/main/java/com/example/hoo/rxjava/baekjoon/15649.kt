package com.example.hoo.rxjava.baekjoon

import java.util.*

private lateinit var visited: Array<Boolean>
private var arraySize = 0
private var count = 0

fun main(args: Array<String>) {
    val input = Scanner(System.`in`).nextLine()

    val (first, second) = input.split(" ")

    arraySize = first.toInt()
    count = second.toInt()

    visited = Array(arraySize) { false }

    for (i in 0 until arraySize) {
        var result = ""
        visited[i] = true
        result += (i + 1)
        result += " "
        progression(0, result)
        visited[i] = false
    }
}

private fun progression(depth: Int, result: String) {
    if (depth == count - 1) {
        println(result.trim())
        return
    }

    for (i in 0 until arraySize) {
        if (!visited[i]) {
            visited[i] = true
            val newResult = result + (i + 1) + " "
            progression(depth + 1, newResult)
            visited[i] = false
        }
    }
}