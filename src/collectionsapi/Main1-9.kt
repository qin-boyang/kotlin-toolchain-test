package com.mycompany.myproject.syntaxsugar

fun frequencySort(s: String): String {
    // TODO: Combine groupingBy, eachCount, entries, sortedByDescending, and flatMap/joinToString
    val result = s.groupingBy { it }.eachCount()
        .entries
        .sortedByDescending { (_, count) -> count }
        .map { (first, second) ->
            first.toString().repeat(second)
        }
        .joinToString("")

    return result
}

fun main() {
    println("Result 1: ${frequencySort("tree")}")     // Expected: "eert" or "eetr"
    println("Result 2: ${frequencySort("cccaaa")}")   // Expected: "cccaaa" or "aaaccc"
    println("Result 3: ${frequencySort("Aabb")}")     // Expected: "bbAa" or "bbaA"
}