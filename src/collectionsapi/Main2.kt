package com.mycompany.myproject.collectionsapi

private fun groupAnagrams(words: List<String>): List<List<String>> {
    // TODO: Solve this using Kotlin collection operations in 1 or 2 lines!
    val map = words.groupBy { word ->
        word.groupingBy { char -> char }.eachCount()
    }
    val list = map.values.toList()
    return list
}

fun main() {
    val input = listOf("eat", "tea", "tan", "ate", "nat", "bat")
    val result = groupAnagrams(input)

    println("Result:")
    result.forEach { println(it) }
}