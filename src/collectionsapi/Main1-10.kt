package com.mycompany.myproject.syntaxsugar

private fun groupAnagrams(words: List<String>): List<List<String>> {
    // TODO: Solve this using a single line of Kotlin collection magic!
    val list = words.groupBy { word -> word.groupingBy { character -> character }.eachCount() }
        .values
        .toList()
    return list
}

fun main() {
    val input = listOf("eat", "tea", "tan", "ate", "nat", "bat")
    val result = groupAnagrams(input)

    println("Result:")
    result.forEach { println(it) }
}