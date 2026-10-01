package com.mycompany.myproject.syntaxsugar

import kotlin.math.abs

fun maxAdjacentDiff(nums: List<Int>): Int {
    // TODO: Combine zipWithNext, maxOrNull, and the Elvis operator (?:)
    if (nums.size < 2) return 0

    val result = nums.windowed(2)
        .map { list -> abs(list[0] - list[1]) }
        .max()

    return result
}

fun main() {
    val nums = listOf(1, 5, 2, 9, 3)
    println("Max Diff: ${maxAdjacentDiff(nums)}") // Expected: 7

    val shortList = listOf(5)
    println("Short List Diff: ${maxAdjacentDiff(shortList)}") // Expected: 0
}