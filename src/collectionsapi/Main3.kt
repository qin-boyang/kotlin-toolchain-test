package com.mycompany.myproject.collectionsapi

fun main() {
    val arr = arrayOf("900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org")
    val flattenentrylist = arr.map { splitBySpace(it) }
        .flatMap { it.entries }
    val groupped : Map<String, List<Map.Entry<String, Int>>> = flattenentrylist.groupBy { it.key }

    val final : List<Pair<String, Int>> = groupped.map {
        val k : String = it.key
        val v : List<Map.Entry<String, Int>> = it.value
        val sum = v.map {it.value}.sum()
        return@map k to sum
    }
    println(final)
}

private fun splitBySpace(s: String): Map<String, Int> {
    val list = s.split(" ")
    val count = list[0].toInt()
    val subdomains = list[1].split(".")
    // map of (subdomain to count)
    val subdomain1 = subdomains.drop(0).joinToString(".")
    val subdomain2 = subdomains.drop(1).joinToString(".")
    val subdomain3 = subdomains.drop(2).joinToString(".")
    val map = mutableMapOf<String, Int>(
        subdomain1 to count,
        subdomain2 to count,
        subdomain3 to count
    ).filter {it.key.isNotBlank()}
    return map
}