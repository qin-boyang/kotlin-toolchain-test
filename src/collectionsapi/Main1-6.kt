package com.mycompany.myproject.syntaxsugar

fun missingNumber(nums: List<Int>): Int {
    // TODO: Use a Kotlin Range (0..nums.size) and either .sum() or Set subtraction
    val range = (0..nums.size).toList()
    val remaining = range - nums
    return remaining.first()
}

fun main() {
    println("Missing: ${missingNumber(listOf(3, 0, 1))}")
    // Expected: 2

    println("Missing: ${missingNumber(listOf(9, 6, 4, 2, 3, 5, 7, 0, 1))}")
    // Expected: 8
}