package com.example.hoo.rxjava.baekjoon

import java.util.*
import kotlin.math.abs
import kotlin.math.min

private lateinit var connected: BooleanArray
private lateinit var planets: List<Triple<Int, Int, Int>>
private var totalDist = 0

fun main(args: Array<String>) {
    val source = ArrayList<String>()
    val sc = Scanner(System.`in`)

    while (sc.hasNextLine()) {
        source.add(sc.nextLine())
    }

    source.removeAt(0)

//    source.add("11 -15 -15")
//    source.add("14 -5 -15")
//    source.add("-1 -1 -5")
//    source.add("10 -4 -1")
//    source.add("19 -4 19")

    connected = BooleanArray(source.size) { false }

    planets = source.map {
        val split = it.split(" ")
        Triple(split[0].toInt(), split[1].toInt(), split[2].toInt())
    }

    connected[0] = true
    findDest(0)

    print(totalDist)
}

private fun findDest(origin: Int) {
    if (!connected.contains(false)) {
        return
    }

    var minDist = -1
    var minIdx = -1

    for (i in 1 until planets.size) {
        if (!connected[i]) {
            val dist = min(planets[origin], planets[i])

            if (minDist == -1 || minDist > dist) {
                minDist = dist
                minIdx = i
            }
        }
    }

    if (minDist != -1) {
        totalDist += minDist
        connected[minIdx] = true
        findDest(minIdx)
    }

}

private fun min(origin: Triple<Int, Int, Int>, dest: Triple<Int, Int, Int>): Int {
    return min(min(abs(origin.first - dest.first), abs(origin.second - dest.second))
            , abs(origin.third - dest.third))
}