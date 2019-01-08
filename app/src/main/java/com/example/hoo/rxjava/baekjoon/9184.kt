package com.example.hoo.rxjava.baekjoon

import java.util.*

private val dp = Array(21) { Array(21) { IntArray(21) { -1 } } }

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    while (true) {
        val a = sc.nextInt()
        val b = sc.nextInt()
        val c = sc.nextInt()

        if (a == -1 && b == -1 && c == -1)
            break

        println("w($a, $b, $c) = ${w(a, b, c)}")
    }
}

private fun w(a: Int, b: Int, c: Int): Int {
    return when {
        a <= 0 || b <= 0 || c <= 0 -> 1
        a > 20 || b > 20 || c > 20 -> w(20, 20, 20)
        dp[a][b][c] != -1 -> dp[a][b][c]
        a < b && b < c -> {
            dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c)
            dp[a][b][c]
        }
        else -> {
            val memo = { a :Int, b :Int, c :Int ->
                val sum = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1)
                sum
            }

            memo.invoke(a,b,c)
        }
    }
}

class MemoizeHelper<in T, out R>(private val function: (T) -> R) : (T) -> R {
    private val valuesMap = mutableMapOf<T, R>()

    override fun invoke(key: T): R = valuesMap.getOrPut(key) {
        function(key)
    }
}