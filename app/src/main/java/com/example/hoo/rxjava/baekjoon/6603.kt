package com.example.hoo.rxjava.baekjoon

import java.util.*

private lateinit var currentInput : List<Int>
private lateinit var include : Array<Boolean>

fun main(args: Array<String>) {
    val source = ArrayList<String>()
    val sc = Scanner(System.`in`)

    while (sc.hasNextLine()) {
        source.add(sc.nextLine())
    }

//    source.add("7 1 2 3 4 5 6 7")
//    source.add("8 1 2 3 5 8 13 21 34")
//    source.add("0")

    source.forEach {
        if(it.length != 1) {
            val input = it.split(" ")
            currentInput = input.subList(1, input.size).map { str: String ->  str.toInt() }
            include = Array(currentInput.size) { false }
            getLottoNum(0)
            print("\n")
        }
    }
}

private fun getLottoNum(level : Int) {
    if(level == currentInput.size) {
        var result = ""
        var count = 0
        for(i in 0 until currentInput.size) {
            if(include[i]) {
                result += currentInput[i].toString() + " "
                count++
            }

        }

        if(count == 6)
            print("$result\n")

        return
    }

    include[level] = true
    getLottoNum(level + 1)
    include[level] = false
    getLottoNum(level + 1)
}