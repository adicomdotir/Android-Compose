package com.example.composepractice

fun calculateAlgorithm(n: Int, k: Int): Result {
    val numbers: MutableList<Int> = mutableListOf()
    val died: MutableList<Int> = mutableListOf()
    for (i in 1..n) {
        numbers.add(i)
    }
    var idx = 0
    var cnt = 1;
    while (numbers.size > 1) {
        if (cnt % k == 0) {
            val removed = numbers.removeAt(idx)
            died.add(removed)
            idx--
        }
        cnt++
        idx++
        idx %= numbers.size
    }
    return Result(numbers.last(), died)
}

data class Result(val save: Int, val died: List<Int>)