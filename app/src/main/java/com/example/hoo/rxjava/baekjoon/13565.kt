package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.collections.ArrayList

private val visited = ArrayList<Pair<Int,Int>>()

fun main(args: Array<String>) {
    val source = ArrayList<String>()
    val sc = Scanner(System.`in`)

    while (sc.hasNextLine()) {
        source.add(sc.nextLine())
    }

//    source.add("8 8")
//    source.add("11000111")
//    source.add("01100000")
//    source.add("00011001")
//    source.add("11001000")
//    source.add("10001001")
//    source.add("10111100")
//    source.add("01010000")
//    source.add("00001011")

    val inputSize = source[0].split(" ")

    val input = Array(inputSize[0].toInt()) { Array(inputSize[1].toInt()) { 0 } }

    source.removeAt(0)

    for(i in 0 until source.size) {
        val arr = source[i].toCharArray()
        val row = input[i]

        arr.forEachIndexed { index, s ->
            row[index] = arr[index].toString().toInt()
        }

        input[i] = row
    }

    input.findOut()
}

private fun Array<Array<Int>>.findOut() {
    val stack = Stack<Pair<Int,Int>>()

    if(this.size == 1) {
        this[0].forEach {
            if(it == 0) {
                print("YES\n")
                return
            }
        }
    } else {
        for(i in 0 until this[0].size) {
            val visit = Pair(0, i)

            if(!visited.contains(visit) && this[visit.first][visit.second] == 0 ) {
                stack.push(visit)

                while (stack.isNotEmpty()) {
                    val pop = stack.pop()
                    visited.add(pop)

                    this.canGoList(pop).forEach {
                        if(it.first == this.size -1 && this[it.first][it.second] == 0) {
                            print("YES\n")
                            return
                        }

                        if(!visited.contains(it)) {
                            stack.push(it)
                        }
                    }
                }
            }
        }
    }

    print("NO\n")
}

private fun Array<Array<Int>>.canGoList(point : Pair<Int,Int>) : ArrayList<Pair<Int,Int>>{
    val result = ArrayList<Pair<Int,Int>>()
    val (first, sec) = point

    if(sec != 0) {
        val left = this[first ][sec - 1]

        if(left == 0) {
            val leftP = Pair(first, sec -1)

            if(!visited.contains(leftP))
                result.add(leftP)
        }
    }

    if(first != 0) {
        val up = this[first -1][sec]

        if(up == 0) {
            val upP = Pair(first -1, sec)

            if(!visited.contains(upP))
                result.add(upP)
        }

    }

    if(sec != this[0].size -1) {
        val right = this[first][sec + 1]

        if(right == 0) {
            val rightP = Pair(first, sec + 1)

            if(!visited.contains(rightP))
                result.add(rightP)
        }
    }

    if(first != this.size -1) {
        val down = this[first + 1][sec]

        if(down == 0) {
            val downP = Pair(first +1, sec)

            if(!visited.contains(downP))
                result.add(downP)
        }
    }

    return result
}

