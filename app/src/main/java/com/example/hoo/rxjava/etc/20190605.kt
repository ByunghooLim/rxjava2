package com.example.hoo.rxjava.etc

private val input = intArrayOf(1, 1, 1, 1, 1)
private val target = 3
private var totalCount = 0

//fun main(args: Array<String>) {
//    sums(1 , input[0])
//    sums(1 , -input[0])
//
//    println(totalCount)
//}
//
//private fun sums(depth: Int, sum: Int) {
//    if (depth == input.size) {
//        if(sum == target)
//            totalCount++
//        return
//    }
//
//    sums(depth + 1, sum + input[depth])
//    sums(depth + 1, sum - input[depth])
//}

fun main(args: Array<String>) {
//    val one = listOf(2, 3, 4, 5, 6, 7, 8, 9)
//    val two = listOf<(Int) -> Int>({ it * 1 }, { it * 2 }, { it * 3 })
//
//    val result = one.flatMap {one ->
//        two.map {
//            "${it(one)}__"
//        }
//    }.joinToString()
//
//    println(result)

}


open class Fruit

class Apple : Fruit()

open class Animal

class Dog : Animal()

interface Test<in T , out K>{
    fun print() : K
}
