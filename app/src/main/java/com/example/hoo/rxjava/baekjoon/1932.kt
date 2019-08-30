package com.example.hoo.rxjava.baekjoon

import java.util.*

private var triangle: ArrayList<ArrayList<Int>> = ArrayList()
private var maxSum = Int.MIN_VALUE
private var count  = 0
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val inputList = ArrayList<String>()

    while (input.hasNext()) {
        inputList.add(input.next())
    }

//    inputList.add("5")
//       inputList.add("7")
//      inputList.add("8 3")
//     inputList.add("0 1 8")
//    inputList.add("4 4 7 2")
////    inputList.add("4 5 2 6 5")
////    inputList.add("7 7 7 7 7 7")

    inputList.removeAt(0)

    for (i in 0 until inputList.size) {
        triangle.add(inputList[i].split(" ").map { it.toInt() }.toCollection(ArrayList()))
    }

    sum(0, 0, triangle[0][0])

    println(maxSum)
    //println(count)
}

private fun sum(depth: Int, idx : Int,  sum: Int) {
    if (depth == triangle.size -1) {
        count++

        if (maxSum < sum)
            maxSum = sum
        return
    }

    if(depth + 1 < triangle.size) {
        sum(depth + 1, idx, sum + triangle[depth + 1][idx])

        if(idx + 1 < triangle[depth + 1].size)
            sum(depth + 1, idx + 1, sum + triangle[depth + 1][idx + 1])
    }
}