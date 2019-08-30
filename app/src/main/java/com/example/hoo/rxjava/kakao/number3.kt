package com.example.hoo.rxjava.kakao

fun main(args: Array<String>) {

    //val results = sentTimes(4, 2, arrayOf(0,2,6))
    val results = sentTimes(5, 3, arrayOf(1, 6, 11, 16))

    println(results.joinToString("\n"))
}

fun sentTimes(numberOfPorts: Int, transmissionTime: Int, packetIds: Array<Int>): Array<Int> {
    val portStatus = Array(numberOfPorts) { 0 }
    val portList = ArrayList<Int>()
    var time = 1

    packetIds.forEach { packetId -> // 패킷 아이디 하나씩 받아오기
        val mod = packetId % numberOfPorts
        var portIdx = mod

        while (true) { // 포트를 순회 하면서 찾는다
            //
            if (portIdx >= numberOfPorts) // 마지막 포트가 사용중이면 다음포트를 찾는데 그게 마지막이까 다시 처음으로 (오버플로우)
                portIdx = 0

            if (portStatus[portIdx] < time) { // 포트가 사용중이 아니면
                portStatus[portIdx] += transmissionTime // 보내고 전송 시간을 세팅한다

                portStatus.forEachIndexed { idx, _ ->
                    if (idx != portIdx && portStatus[idx] < time) // 선택된 놈하고 포트가 사용중인게 아니면 시간 을 1씩 증가시켜준다.
                        portStatus[idx]++
                }

                portList.add(portIdx)// 사용된 포트 넣어줌
                break
            }

            portIdx++ // 사용중이면 다음 포트로 간다

            if (portIdx == mod) // 패킷 자리가 다 차서 한바퀴 돌아서 자리가 없으니까 1초 기다린다.
                time++

        }
        time++ // 시간이 1초 흘렀다
    }

    return portList.toTypedArray()
}