package com.example.hoo.rxjava.kakao

fun main(args: Array<String>) {

    val test = "0011223344556677889988"


    println(numOfIds(test))
}

fun numOfIds(pool: String): Int {
    val max = pool.length / 11
    val numberOf8 = pool.filter {
        it == '8'
    }.count()

    return kotlin.math.min(max, numberOf8)
}