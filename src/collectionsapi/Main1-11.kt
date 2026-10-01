package com.mycompany.myproject.syntaxsugar

fun findDisappearedNumbers(nums: List<Int>): List<Int> {
    // TODO: Use a range from 1 to nums.size and set subtraction
    val list = (1..nums.size).toList() - nums
    return list
}

fun main() {
    val nums = listOf(4, 3, 2, 7, 8, 2, 3, 1)
    val result = findDisappearedNumbers(nums)

    println("Disappeared Numbers: $result") // Expected: [5, 6]
}