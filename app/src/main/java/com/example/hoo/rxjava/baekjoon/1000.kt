package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.collections.ArrayList

private lateinit var completed : BooleanArray
private lateinit var input : List<Pair<Int,Int>>
private val resultArray = ArrayList<Int>()

fun main(args: Array<String>) {
    val source = ArrayList<String>()
//    val sc = Scanner(System.`in`)
//
//    while (sc.hasNextLine()) {
//        print("${sc.nextLine()}\n")
//    }
    source.add("4 60")
    source.add("4 40")
    source.add("1 20")
    source.add("2 50")
    source.add("3 30")
    source.add("4 10")
    source.add("6 5")

    input = source.map {
        val split = it.split(" ")
        Pair(split[0].toInt(), split[1].toInt())
    }

    completed = BooleanArray(input.size) { false }

    input.forEachIndexed { index, pair ->
        doHomework(index, 1, 0)
    }

    print(resultArray.max())
}

private fun doHomework(idx : Int, dday : Int, sum : Int) {
    if(idx == input.size) {
        resultArray.add(sum)
        return
    }

    var newSum = sum

    completed[idx] = true

    if(dday <= input[idx].first) {
        newSum += input[idx].second
    }

    input.forEachIndexed { index, pair ->
        doHomework(index, dday + 1, newSum)
    }
}