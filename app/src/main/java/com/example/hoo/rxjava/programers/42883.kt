package com.example.hoo.rxjava.programers

private lateinit var memo: Array<Boolean>
private lateinit var inputStr: String

private lateinit var answer: String
private var k: Int = 0

fun main(args: Array<String>) {
    answer = "0"
    k = 4

    inputStr = "4177252841"
    memo = Array(inputStr.length) { false }

    computeNum(0)

    print(answer)
}

private fun computeNum(idx: Int) {
    if (idx == inputStr.length) {
        val result = getNumFromMemo()

        if (result.length == inputStr.length - k && answer.toBigInteger() <= result.toBigInteger()) {
            answer = result
        }

        return
    }

    memo[idx] = true
    computeNum(idx + 1)
    memo[idx] = false
    computeNum(idx + 1)
}

private fun getNumFromMemo(): String {
    var result = ""

    memo.forEachIndexed { index, bool ->
        if (bool)
            result += inputStr[index]
    }

    return result
}