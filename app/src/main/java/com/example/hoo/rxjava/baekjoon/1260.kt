package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.collections.ArrayList

private lateinit var dataList: ArrayList<Pair<Int, Int>>
private var totalNodes = 0
fun main(args: Array<String>) {
    //val input = Scanner(System.`in`)
    val inputList = ArrayList<String>()

//    while (input.hasNext()) {
//        inputList.add(input.next())
//    }

//    inputList.add("4 5 1")
//    inputList.add("1 2")
//    inputList.add("1 3")
//    inputList.add("1 4")
//    inputList.add("2 4")
//    inputList.add("3 4")

    inputList.add("5 5 3")
    inputList.add("5 4")
    inputList.add("5 2")
    inputList.add("1 2")
    inputList.add("3 4")
    inputList.add("3 1")

    val (count, nodes, startPoint) = inputList[0].split(" ").map { it.toInt() }

    inputList.removeAt(0)

    totalNodes = nodes
    dataList = inputList.map { str ->
        val spited = str.split(" ").map {
            it.toInt()
        }
        Pair(spited[0], spited[1])
    }.toCollection(ArrayList())

    doDFS(startPoint, count)
    doBFS(startPoint, count)
}

private fun doBFS(startPoint: Int, count: Int) {
    val visitedBFS = ArrayList<Int>()

    bfs(startPoint, ArrayDeque(), visitedBFS)

    if (!visitedBFS.contains(count))
        visitedBFS.add(count)

    println(visitedBFS)
}

private fun doDFS(startPoint: Int, count: Int) {
    val visitedDFS = ArrayList<Int>()

    dfs(startPoint, visitedDFS)

    for(i in 0 until count) {
        dfs(dataList[i].first, visitedDFS)
    }

    println(visitedDFS)
}

private fun dfs(dest: Int, visited: ArrayList<Int>) {
    if (!visited.contains(dest)) {
        visited.add(dest)
    }

    val destList = ArrayList<Int>()

    for (i in 0 until dataList.size) {
        if (dest == dataList[i].first) {
            destList.add(dataList[i].second)
        }
    }

    destList.sort()

    if (destList.isNotEmpty()) {
        dfs(destList[0], visited)
    }
}

private fun bfs(dest: Int, queue: ArrayDeque<Int>, visited: ArrayList<Int>) {
    if (!visited.contains(dest)) {
        visited.add(dest)
    }

    val destList = ArrayList<Int>()

    for (i in 0 until dataList.size) {
        if (dest == dataList[i].first) {
            destList.add(dataList[i].second)
        }
    }

    destList.sort()

    destList.forEach {
        queue.offer(it)
    }

    if (queue.isNotEmpty()) {
        bfs(queue.poll(), queue, visited)
    }
}