package com.mycompany.myproject.syntaxsugar

fun moveZeroes(nums: List<Int>): List<Int> {
    // TODO: Use partition and the + operator
    val result = nums.partition { it != 0 }
    val result1 = result.first
    val result2 = result.second
    return result1 + result2
}

fun main() {
    val nums = listOf(0, 1, 0, 3, 12)
    val result = moveZeroes(nums)
    println("Result: $result") // Expected: [1, 3, 12, 0, 0]
}