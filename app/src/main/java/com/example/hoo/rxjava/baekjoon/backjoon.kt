package com.example.hoo.baekjoon

import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val array = ArrayList<String>()
    val sc = Scanner(System.`in`)

    while (sc.hasNextLine()) {
        array.add(sc.nextLine())
    }

//    array.add("4 5 1")
//
//    array.add("1 2")
//    array.add("1 5")
//
//    array.add("2 1")
//    array.add("2 3")
//    array.add("2 4")
//    array.add("2 5")
//
//    array.add("3 2")
//    array.add("3 4")
//
//    array.add("4 3")
//    array.add("4 5")
//    array.add("4 2")
//    array.add("4 6")
//
//    array.add("5 1")
//    array.add("5 2")
//    array.add("5 4")
//
//    array.add("6 4")

    val startLine = array[0]

    array.removeAt(0)

    val startIdx = (startLine.split(" "))[2].toInt()

    val datas = HashMap<Int, ArrayList<Int>>()

    array.forEach {
        val splited = it.split(" ")

        val start = splited[0].toInt()
        val dest = splited[1].toInt()

        datas.addNode(start, dest)
    }

    datas.dfs(startIdx)
    datas.bfs(startIdx)
}

private fun HashMap<Int, ArrayList<Int>>.addNode(start : Int, dest : Int) {
    val arr : ArrayList<Int>?

    if(containsKey(start)) {
        arr = this[start]

        if(arr?.contains(dest) == false) {
            arr.add(dest)
            this[start] = arr

            this.addNode(dest, start)
        }
    } else {
        arr = ArrayList()
        arr.add(dest)

        this[start] = arr

        this.addNode(dest, start)
    }
}

private fun Map<Int, ArrayList<Int>>.dfs(start : Int) {
    val resultArr = ArrayList<Int>()
    val stack = Stack<Int>()

    resultArr.add(start)

    val destArr = get(start)

    if(destArr?.isNotEmpty() == true) {
        destArr.forEach {
            stack.push(it)

            while (stack.isNotEmpty()) {
                val pop = stack.pop()

                if(!resultArr.contains(pop))
                    resultArr.add(pop)

                val dests = this[pop]

                for(i in dests!!.size -1 downTo 0) {
                    if(!resultArr.contains(dests[i]))
                        stack.push(dests[i])
                }
//                dests?.forEach {
//                    if(!resultArr.contains(it) && !stack.contains(it))
//                        stack.push(it)
//                }
            }
        }

    }

    resultArr.print()
}

private fun Map<Int, ArrayList<Int>>.bfs(start : Int) {
    val resultArr = ArrayList<Int>()
    val queue = LinkedBlockingQueue<Int>()

    resultArr.add(start)

    val destArr = get(start)

    if(destArr?.isNotEmpty() == true) {
        destArr.forEach {
            if(!resultArr.contains(it))
                queue.add(it)
        }

        while (queue.isNotEmpty()) {
            val pop = queue.poll()

            if(!resultArr.contains(pop))
                resultArr.add(pop)

            val dests = this[pop]

            dests?.forEach {
                if(!resultArr.contains(it))
                    queue.add(it)
            }
        }

    }

    resultArr.print()
}

private fun ArrayList<Int>.print() {
    var result = ""

    forEach {
        result += "$it "
    }

    print("${result.substring(0, result.length -1)}\n")
}
