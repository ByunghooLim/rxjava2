package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.collections.ArrayList

private var cell: ArrayList<ArrayList<Int>> = ArrayList()
private var columnSize = 0
private var rowSize = 0
private var minVirusSize = Int.MAX_VALUE
private lateinit var visited: ArrayList<Pair<Int, Int>>

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val inputList = ArrayList<String>()

    while (input.hasNext()) {
        inputList.add(input.next())
    }

//    inputList.add("4 6")
//    inputList.add("0 0 0 0 0 0")
//    inputList.add("1 0 0 0 0 2")
//    inputList.add("1 1 1 0 0 2")
//    inputList.add("0 0 0 0 0 2")

//    inputList.add("7 7")
//    inputList.add("2 0 0 0 1 1 0")
//    inputList.add("0 0 1 0 1 2 0")
//    inputList.add("0 1 1 0 1 0 0")
//    inputList.add("0 1 0 0 0 0 0")
//    inputList.add("0 0 0 0 0 1 1")
//    inputList.add("0 1 0 0 0 0 0")
//    inputList.add("0 1 0 0 0 0 0")

    val (first, second) = inputList[0].split(" ").map { it.toInt() }
    columnSize = first
    rowSize = second

    for (i in 1 until columnSize + 1) {
        cell.add(inputList[i].split(" ").map { it.toInt() }.toCollection(ArrayList()))
    }

    val safezoneSize = getSafeZoneSize() - 3
    val originVirusSize = getVirusSize()
    setUpWall(0)
    println(safezoneSize - (minVirusSize - originVirusSize))
}

private fun setUpWall(count: Int) {
    if (count == 3) {
        visited = ArrayList()
        findVirusAndInfest()

        if (minVirusSize > visited.size)
            minVirusSize = visited.size

        return
    }

    for (i in 0 until cell.size) {
        for (j in 0 until cell[i].size) {
            if (cell[i][j] == 0) {
                cell[i][j] = 1
                setUpWall(count + 1)
                cell[i][j] = 0
            }
        }
    }
}

private fun findVirusAndInfest() {
    val nextQueue = ArrayDeque<Pair<Int, Int>>()

    for (i in 0 until cell.size) {
        for (j in 0 until cell[i].size) {
            if (cell[i][j] == 2)
                infest(i, j, nextQueue)
        }
    }
}

private fun infest(col: Int, row: Int, nextQueue: ArrayDeque<Pair<Int, Int>>) {
    val point = Pair(col, row)

    if (!visited.contains(point)) {
        visited.add(point)
        enQueue(col, row, nextQueue)
    }

    if (nextQueue.isNotEmpty()) {
        val (first, sec) = nextQueue.poll()
        infest(first, sec, nextQueue)
    }
}

private fun enQueue(col: Int, row: Int, nextQueue: ArrayDeque<Pair<Int, Int>>) {
    if (col > 0) {
        val newCol = col - 1
        val pair = Pair(newCol, row)

        if (cell[newCol][row] == 0 && !visited.contains(pair))
            nextQueue.offer(pair)
    }

    if (col < columnSize - 1) {
        val newCol = col + 1
        val pair = Pair(newCol, row)

        if (cell[newCol][row] == 0 && !visited.contains(pair))
            nextQueue.offer(pair)
    }

    if (row > 0) {
        val newRow = row - 1
        val pair = Pair(col, newRow)

        if (cell[col][newRow] == 0 && !visited.contains(pair))
            nextQueue.offer(pair)
    }

    if (row < rowSize - 1) {
        val newRow = row + 1
        val pair = Pair(col, newRow)

        if (cell[col][newRow] == 0 && !visited.contains(pair))
            nextQueue.offer(pair)
    }
}

private fun getSafeZoneSize(): Int {
    var size = 0

    for (i in 0 until columnSize) {
        for (j in 0 until rowSize) {
            if (cell[i][j] == 0)
                size += 1
        }
    }

    return size
}

private fun getVirusSize(): Int {
    var size = 0

    for (i in 0 until columnSize) {
        for (j in 0 until rowSize) {
            if (cell[i][j] == 2)
                size += 1
        }
    }

    return size
}