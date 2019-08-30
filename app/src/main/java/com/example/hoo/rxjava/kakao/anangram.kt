package com.example.hoo.rxjava.kakao

fun main(args: Array<String>) {

    val test = arrayOf("code", "code", "code", "code")

    val result = funWithAnagrams(test)

    println(result.joinToString("\n"))
}

fun funWithAnagrams(s: Array<String>): Array<String> {
    val deleteIdx = ArrayList<Int>()

    for (i in 0 until s.size) {
        val dest = s[i]

        if (deleteIdx.contains(i))
            continue

        for (j in i + 1 until s.size) {
            var target = s[j]

            if (target == dest) {
                deleteIdx.add(j)
                continue
            }


            dest.forEach {
                target = target.replaceFirst(it, ' ', false)
            }

            if (target.isBlank() && (target.length == dest.length))
                deleteIdx.add(j)
        }
    }

    return s.filterIndexed { index, _ ->
        !deleteIdx.contains(index)
    }.sorted().toTypedArray()
}