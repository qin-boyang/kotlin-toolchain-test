package com.mycompany.myproject.syntaxsugar

fun flattenAndSort(nestedLists: List<List<Int>>): List<Int> {
    // TODO: Combine flatten, distinct/toSet, and sorted
    val result = nestedLists.flatten()
        .distinct()
        .sorted()
    return result
}

fun main() {
    val input = listOf(
        listOf(1, 2, 3),
        listOf(2, 3, 4),
        listOf(4, 5, 6)
    )

    val result = flattenAndSort(input)
    println("Result: $result") // Expected: [1, 2, 3, 4, 5, 6]
}