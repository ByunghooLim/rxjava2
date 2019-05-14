package com.example.hoo.rxjava.sort

import java.util.*

var array = intArrayOf(4, 6, 8, 2, 9, 1, 0, 5, 3, 7)

fun main(args: Array<String>) {

    println(Arrays.toString(array))

    quick(0, array.size -1)

    print(Arrays.toString(array))
}

fun quick(left: Int, right: Int) {
    var leftValue = left
    var rightValue = right
    var pivot = ((right - left) / 2)
    val pivotConstrant = pivot

    val pivotValue = array[pivot]

    do {
        var updatedLeftValue = 0

        for (l in leftValue until pivotConstrant) {
            updatedLeftValue = l

            if (array[l] > pivotValue) {
                insert(l, pivot + 1 )
                pivot--
                break
            }
        }

        leftValue = updatedLeftValue

        var updatedRightValue = 0

        for (r in rightValue downTo pivotConstrant) {
            updatedRightValue = r

            if (array[r] < pivotValue) {
                insert(r, pivot - 1 )
                pivot++
                break
            }
        }

        rightValue = updatedRightValue

    } while (leftValue <= rightValue)

    quick(0,pivot -1)
    quick(pivot + 1 , array.size -1)
}

fun insert(dest: Int, target: Int) {
    val destValue = array[dest]
    val stack = Stack<Int>()

    for (i in 0 until array.size) {
        if (i == target) {
            stack.push(array[i])
            stack.push(destValue)
        } else if (i != dest) {
            stack.push(array[i])
        }
    }

    array = stack.toIntArray()
}