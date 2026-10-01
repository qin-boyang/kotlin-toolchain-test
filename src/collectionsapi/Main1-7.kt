package com.mycompany.myproject.syntaxsugar

fun intersection(nums1: List<Int>, nums2: List<Int>): List<Int> {
    // TODO: Use toSet() and intersect
    val set1 = nums1.toSet()
    val set2 = nums2.toSet()
    val intersection = set1.intersect(set2)
    return intersection.toList()
}

fun main() {
    val nums1 = listOf(1, 2, 2, 1)
    val nums2 = listOf(2, 2)
    println("Intersection 1: ${intersection(nums1, nums2)}") // Expected: [2]

    val nums3 = listOf(4, 9, 5)
    val nums4 = listOf(9, 4, 9, 8, 4)
    println("Intersection 2: ${intersection(nums3, nums4)}") // Expected: [4, 9] (or [9, 4])
}