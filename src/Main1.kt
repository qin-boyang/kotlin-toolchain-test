package com.mycompany.myproject

// Reverse each word in a string. Input - This is an Interview

fun main() {
    val s1 = "This is an Interview"

    val result = s1.split(" ")
        .map { it.reversed() }
        .joinToString(" ")


    println(result)
}