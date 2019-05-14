package com.example.hoo.rxjava.baekjoon

import java.util.*

private lateinit var visited: Array<Boolean>
private var count = 0

fun main(args: Array<String>) {
    count = Scanner(System.`in`).nextInt()

    visited = Array(count) { false }

    for (i in 0 until count) {
        var result = ""
        visited[i] = true
        result += (i + 1)
        permutation(0, result)
        visited[i] = false
    }
}

fun permutation(depth: Int, result: String) {
    if (depth == count - 1) {
        println(result)
        return
    }

    for (i in 0 until count) {
        if (!visited[i]) {
            visited[i] = true
            val newResult = result + (i + 1)
            permutation(depth + 1, newResult)
            visited[i] = false
        }
    }
}