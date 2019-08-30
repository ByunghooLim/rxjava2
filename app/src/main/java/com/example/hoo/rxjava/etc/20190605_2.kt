package com.example.hoo.rxjava.etc

private val input = arrayOf(Pair(4, 3), Pair(4, 2), Pair(3, 2), Pair(1, 2), Pair(2, 5))
private val rank = Array(input.size) { -1 }
private val map = HashMap<Int, Pair<Int, Int>>()
private var count = 0

fun main(args: Array<String>) {
    input.forEach {
        val (winner, loser) = it

        if (map.containsKey(winner)) {
            var (winCount, loseCount) = map.get(winner)!!
            map.set(winner, Pair(winCount + 1, loseCount))
        } else {
            map.set(winner, Pair(1, 0))
        }

        if (map.containsKey(loser)) {
            var (winCount, loseCount) = map.get(loser)!!
            map.set(loser, Pair(winCount, loseCount + 1))
        } else {
            map.set(loser, Pair(0, 1))
        }
    }

    map.forEach {
        val (winCount, loseCount) = it.value

        if (winCount + loseCount == input.size - 1) {
            rank[loseCount + 1] = it.key
            count++
        }
    }

    rank.forEachIndexed { index, player ->
        if(player != -1) {
            if(index == 1) {
                input.forEach {
                    val (_, loser) = it

                    if(loser == player)
                        count++
                }
            }

            if(index == rank.size -1) {
                input.forEach {
                    val (winner, _) = it

                    if(winner == player)
                        count++
                }
            }
        }

    }

    println(count)
}



