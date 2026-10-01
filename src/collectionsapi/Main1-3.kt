package com.mycompany.myproject.syntaxsugar

fun firstUniqueChar(s: String): Char? {
    // TODO: Combine groupingBy, eachCount, and firstOrNull
    val result = s.groupingBy { char -> char }.eachCount()
        .filter { (_, count) -> count == 1 }
        .map { (key, count) -> key }
        .firstOrNull()

    return result
}

fun main() {
    println("Result 1: ${firstUniqueChar("leetcode")}")     // Expected: l
    println("Result 2: ${firstUniqueChar("loveleetcode")}") // Expected: v
    println("Result 3: ${firstUniqueChar("aabb")}")         // Expected: null
}