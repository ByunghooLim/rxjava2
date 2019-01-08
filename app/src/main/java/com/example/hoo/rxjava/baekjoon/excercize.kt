package com.example.hoo.rxjava.baekjoon

private val source = ArrayList<Int>()
private lateinit var includes : Array<Boolean>

fun main(args: Array<String>) {
    solution(50)
}

private fun solution(n : Int) : ArrayList<Int> {
    for(i in n * -1 + 1 until n) {
        source.add(i)
    }

    //print(source)

    includes = Array(source.size) { false }

    getZeroSumSet(0, n)

    return source
}

private fun getZeroSumSet(level : Int, n : Int) {
    if(level == source.size) {
        val result = ArrayList<Int>()

        for(i in 0 until source.size) {
            if(includes[i])
                result.add(source[i])
        }

        if(result.size == n && result.sum() == 0)
            print("$result\n")

        return
    }

    includes[level] = true
    getZeroSumSet(level + 1, n)
    includes[level] = false
    getZeroSumSet(level + 1, n)
}