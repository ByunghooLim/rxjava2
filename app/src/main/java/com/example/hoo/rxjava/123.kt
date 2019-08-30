package com.example.hoo.rxjava

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.math.max
import kotlin.math.min

var int = 11

val threadTest: Runnable = Runnable {
    print(int)
}

var disp: Disposable? = null

fun main(args: Array<String>) {
    defer()
    Thread.sleep(100000000L)
}

fun scheduler() {

}

fun delay() {
    Observable.just(2, 4, 6, 8)
            .delay(3000L, TimeUnit.MILLISECONDS)
            .timeInterval()
            .subscribe {
                print("$it\n")
            }
}

fun math() {
    val ob1 = Observable.just(2, 4, 6, 8)

    ob1.count().subscribe({
        print("count = $it\n")
    }) {}

    ob1.to { }
}

fun all() {
    Observable.just(2, 4, 6, 8).all {
        it % 2 == 0
    }.subscribe({
        print(it)
    }, {

    })
}

fun takeUtil() {
    Observable.just(1, 2, 3, 4)
            .takeUntil(Observable.just(5, 6, 7))
            .subscribe {
                print("$it\n")
            }
}

fun concat() {
    val ob1 = Observable.just(1, 2, 3, 4).doOnComplete {
        print("ob1 complete \n")
    }

    val ob2 = Observable.just("a", "b", "c").doOnComplete {
        print("ob2 complete \n")
    }

    Observable.concat(ob1, ob2).subscribe {
        print("$it\n")
    }
}

fun merge() {
    val ob1 = Observable.just(1, 2, 3, 4)
    val ob2 = Observable.just("a", "b", "c")

    Observable.merge(ob1, ob2).subscribe {
        print("$it\n")
    }
}

fun combineLatest() {
    val ob1 = Observable.just(1, 2, 3, 4)
            .zipWith(Observable.interval(500L, TimeUnit.MILLISECONDS), BiFunction<Int, Long, Int> { t1, t2 ->
                t1
            })
    val ob2 = Observable.just("a", "b", "c")
            .zipWith(Observable.interval(1000L, TimeUnit.MILLISECONDS), BiFunction<String, Long, String> { t1, t2 ->
                t1
            })

    Observable.combineLatest(ob1, ob2, BiFunction<Int, String, String> { t1, t2 ->
        t1.toString() + t2
    }).subscribe {
        print("$it\n")
    }

}

fun zipElectric1() {
    val arr = arrayOf(100, 400)

    val baseData = Observable.fromArray(*arr)

    val basePrice = baseData.map {
        when {
            it <= 200 -> 910
            it <= 400 -> 1600
            else -> 7300
        }
    }

    val usagePrice = baseData.map {
        val series1 = min(200, it) * 93.3
        val series2 = min(200, max(it - 200, 0)) * 187.9
        val series3 = min(0, max(it - 400, 0)) * 280.65

        (series1 + series2 + series3).toInt()
    }

    Observable.zip(basePrice, usagePrice, baseData, Function3<Int, Int, Int, String> { i: Int, i1: Int, i2: Int ->
        "$i2 >>>>>> ${i + i1}\n"
    }).subscribe {
        print("$it")
    }
}

fun zipInterval() {
    val print = {
        print("33\n")
    }
    val ob1 = Observable.interval(1000, TimeUnit.MILLISECONDS)
    val ob2 = Observable.just(print)

    Observable.zip(ob1, ob2, BiFunction<Long, () -> Unit, () -> Unit> { t1, t2 ->
        t2
    }).repeat().subscribe {
        it.invoke()
    }

}

fun zip() {
    val ob1 = Observable.just(1, 2, 3, 4, 5)
    val ob2 = Observable.just("a", "b", "c", "c")
    val ob3 = Observable.just(11)

    Observable.zip(ob1, ob2, ob3, Function3<Int, String, Int, String> { t1: Int, t2: String, t3: Int ->
        t1.toString() + t2 + t3.toString()
    }).subscribe {
        print("$it\n")
    }

}

fun groupBy() {
    Observable.just(1, 3, 4, 5, 6).groupBy {
        it % 2 == 0
    }.subscribe { group ->
        group.filter {
            group.key == true
        }.subscribe { int ->
            print("$int\n")
        }
    }
}

fun concatMap() {
    Observable.just(1, 3, 4).concatMap { int ->
        Observable.just("a", "b", "c").map {
            int.toString() + it
        }
    }.subscribe {
        print("$it\n")
    }
}

@SuppressLint("CheckResult")
fun rxAndroid() {
    val test = arrayOf("apple", "melon", "grape", "ddd", "aa").toCollection(ArrayList())

    Observable.fromIterable(test)
            .filter {
                it.contains("a")
            }.subscribe {
                print("$it\n")
            }

}

@SuppressLint("CheckResult")
fun heartBeat() {
    Observable.interval(2000, TimeUnit.MILLISECONDS).map {
        "send ping $it"
    }.repeat().subscribe {
        print("$it\n")
    }

    Thread.sleep(100000000L)
}

@SuppressLint("CheckResult")
fun repeat() {
    Observable.interval(500, TimeUnit.MILLISECONDS).map {
        it + 1
    }.repeat().subscribe {
        print("$it\n")
    }

    Thread.sleep(100000000L)
}

@SuppressLint("CheckResult")
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

@SuppressLint("CheckResult")
fun interval() {
    Observable.interval(1000, TimeUnit.MILLISECONDS).map {
        it
    }.subscribe {
        print("$it\n")
    }
}

@SuppressLint("CheckResult")
fun reduce() {
    Observable.range(2, 3)
            .reduce { t1: Int, t2: Int ->
                t1 * t2
            }.subscribe {
                print("$it\n")
            }
}

@SuppressLint("CheckResult")
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

@SuppressLint("CheckResult")
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

@SuppressLint("CheckResult")
fun flatMap() {
    Observable.just("1", "2").flatMap {
        Observable.just(it + "a")
    }.subscribe {
        print("$it\n")
    }
}

@SuppressLint("CheckResult")
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

@SuppressLint("CheckResult")
fun fromArray() {
    val arr1 = arrayOf(24, 3, 25, 36, 5, 0, 99)
    val source = Observable.fromArray(*arr1)
    source.subscribe {
        print("$it\n")
    }
}

@SuppressLint("CheckResult")
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

@SuppressLint("CheckResult")
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