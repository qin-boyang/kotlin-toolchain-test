package com.mycompany.myproject.syntaxsugar

fun main() {
    val nums = listOf(4, 3, 2, 7, 8, 2, 3, 1)
    val result = findDuplicates(nums)

    println("Duplicates: $result") // Expected: [2, 3] or [3, 2]
}

fun findDuplicates(nums: List<Int>): List<Int> {
    val result = nums.groupingBy { it }.eachCount()
        .filter { it.value > 1 }
        .keys
        .toList()
    return result
}