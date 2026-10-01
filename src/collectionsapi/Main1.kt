package com.mycompany.myproject.syntaxsugar

fun main() {
    val s : String = "anagram"
    val groupS : Map<Char, Int> = s.groupingBy { it }.eachCount()
    val t : String = "nagaram"
    val groupT : Map<Char, Int> = t.groupingBy { it }.eachCount()

    println(groupS)
    println(groupT)
    println(groupS.equals(groupT))
}