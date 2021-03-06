package com.example.hoo.rxjava.baekjoon

import java.util.*

private val houses = ArrayList<Triple<Int, Int, Int>>()
private var min = -1

fun main(args: Array<String>) {
    val source = ArrayList<String>()
//    val sc = Scanner(System.`in`)
//
//    while (sc.hasNextLine()) {
//        source.add(sc.nextLine())
//    }
//
//    source.removeAt(0)

    for (i in 0 until 100) {
        source.add("26 40 83")
        source.add("49 60 57")
        source.add("13 89 99")
    }
//    source.add("26 40 83")
//    source.add("49 60 57")
//    source.add("13 89 99")

    source.forEach {
        val houseSplit = it.split(" ")

        houses.add(Triple(houseSplit[0].toInt()
                , houseSplit[1].toInt()
                , houseSplit[2].toInt()))
    }

    paintHouse(0, 0, 0)
    paintHouse(0, 1, 0)
    paintHouse(0, 2, 0)

    print(min)
}

private fun paintHouse(house: Int, color: Int, sum: Int) {
    val colorVal = getColorValue(house, color)

    if (min != -1 && min <= colorVal)
        return

    val newSum = sum + colorVal

    if (min != -1 && min <= newSum)
        return

    if (house == houses.size - 1) {
        if (min == -1) {
            min = newSum
        } else {
            if (newSum < min)
                min = newSum
        }

        return
    }

    when (color) {
        0 -> {
            paintHouse(house + 1, 1, newSum)
            paintHouse(house + 1, 2, newSum)
        }
        1 -> {
            paintHouse(house + 1, 0, newSum)
            paintHouse(house + 1, 2, newSum)
        }
        else -> { // 2
            paintHouse(house + 1, 0, newSum)
            paintHouse(house + 1, 1, newSum)
        }
    }
}

private fun getColorValue(house: Int, color: Int): Int {
    return when (color) {
        0 -> houses[house].first
        1 -> houses[house].second
        2 -> houses[house].third
        else -> 0
    }
}