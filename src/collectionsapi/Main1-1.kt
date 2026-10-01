package com.mycompany.myproject.syntaxsugar

fun main() {
    val nums = listOf(1, 1, 1, 2, 2, 3)
    val k = 2

    val map = nums.groupingBy { num -> num }.eachCount()
    println(map)
    val sorted = map.entries
        .sortedBy{ it.value}
        .reversed()
        .take(k)
        .map { entry -> entry.key }
    println(sorted)
}