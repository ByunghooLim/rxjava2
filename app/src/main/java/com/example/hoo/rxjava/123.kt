package com.example.hoo.rxjava

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

var int = 11

val threadTest: Runnable = Runnable {
    print(int)
}

var disp: Disposable? = null

fun main(args: Array<String>) {
    //just()
    //create()
    //fromArray()
    //callable()
    //callFuture()
    //map()
    //flatMap()
    //gooGooDan()
    //filter()
    //reduce()
    //create2()
    //interval()
    //intervalRange()

//    intervalRange(2, 10, 1000L).subscribe {
//        print("$it\n")
//    }
//    Thread.sleep(100000000L)
    //defer()
    //repeat()
    heartBeat()
}

fun heartBeat() {
    Observable.interval(2000, TimeUnit.MILLISECONDS).map {
        "send ping $it"
    }.repeat().subscribe {
        print("$it\n")
    }

    Thread.sleep(100000000L)
}

fun repeat() {
    Observable.interval(500, TimeUnit.MILLISECONDS).map {
        it + 1
    }.repeat().subscribe {
        print("$it\n")
    }

    Thread.sleep(100000000L)
}

fun defer() {
    val source = Observable.defer {
        Observable.just(1, 2, 3)
    }

    source.subscribe {
        print("1 = $it\n")
    }

    source.subscribe {
        print("2 = $it\n")
    }

}

fun intervalRange(start: Int, count: Long, perioid: Long): Observable<Long> {
    return Observable.interval(perioid, TimeUnit.MILLISECONDS)
            .map {
                it + start
            }
            .take(count)
}

fun interval() {
    Observable.interval(1000, TimeUnit.MILLISECONDS).map {
        it
    }.subscribe {
        print("$it\n")
    }
}

fun reduce() {
    Observable.range(2, 3)
            .reduce { t1: Int, t2: Int ->
                t1 * t2
            }.subscribe {
                print("$it\n")
            }
}

fun filter() {
    val source = Observable.range(2, 10)

    source.filter {
        it % 2 == 0
    }.doOnSubscribe {
        print("Filter\n")
    }.subscribe {
        print("$it\n")
    }

    source.take(3)
            .doOnSubscribe {
                print("Take\n")
            }
            .subscribe {
                print("$it\n")
            }

    source.first(5)
            .doOnSubscribe {
                print("first\n")
            }.subscribe({
                print("$it\n")
            }, {

            })
}

fun gooGooDan() {
    Observable.range(2, 8).flatMap {
        Observable.range(1, 9).map { it2 ->
            var str = "$it X $it2 = ${it2 * it}"

            if (it2 == 9)
                str += "\n"

            str
        }
    }.subscribe {
        print("$it\n")
    }
}

fun flatMap() {
    Observable.just("1", "2").flatMap {
        Observable.just(it + "a")
    }.subscribe {
        print("$it\n")
    }
}

fun map() {
    Observable.just("1", "2").map {
        it + "dd"
    }.subscribe {
        print("$it\n")
    }
}

fun callFuture() {
    val future = Executors.newSingleThreadExecutor().submit {
        print("ddd")
    }
    val source = Observable.fromFuture(future)
    disp = source.subscribe({
        disp?.dispose()
    }, {
        disp?.dispose()
    }, {
        disp?.dispose()
    })
}

fun callable() {
    val source = Observable.fromCallable {
        print("dd")
    }

    source.subscribe()
}

fun fromArray() {
    val arr1 = arrayOf(24, 3, 25, 36, 5, 0, 99)
    val source = Observable.fromArray(*arr1)
    source.subscribe {
        print("$it\n")
    }
}

fun create2() {
    Observable.create<() -> Unit> {
        it.onNext {
            print("tread1\n")
        }

        Thread.sleep(2000)

        it.onNext {
            print("tread2\n")
        }

        Thread.sleep(2000)

        it.onComplete()
    }.doOnSubscribe {
        print("showProg\n")
    }.subscribe({
        it.invoke()
    }, {
        print("error\n")
        print("hideProg\n")
    }, {
        print("hideProg\n")
    })
}

fun create() {
    val source = Observable.create<() -> Unit> {
        it.onNext {
            print("1\n")
        }
        it.onNext {
            print("2\n")
        }
        it.onComplete()
    }

    source.subscribe({
        it.invoke()
    }, {

    }, {
        print("Complete\n")
    })
}

fun just() {
    val source = Observable.just("RED", "GREEN", "BLUE")

    disp = source.subscribe {
        print("$it\n")
    }

    print("${disp?.isDisposed}\n")
}