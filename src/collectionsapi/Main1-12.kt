package com.mycompany.myproject.syntaxsugar

fun topKFrequentWords(words: List<String>, k: Int): List<String> {
    // TODO: Combine groupingBy, eachCount, entries, sortedWith, take, and map
    val result = words.groupingBy { it }.eachCount()
        .entries
        .sortedByDescending { it.value }
        .take(k)
        .map { it.key }
    return result
}

fun main() {
    val words = listOf("i", "love", "leetcode", "i", "love", "coding")
    val k = 2
    val result = topKFrequentWords(words, k)

    println("Top K Words: $result") // Expected: [i, love]
}