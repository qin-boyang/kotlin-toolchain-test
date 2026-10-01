package com.mycompany.myproject.collectionsapi

data class Transaction(val userId: String, val amount: Double?, val status: String)

val logs = listOf(
    Transaction("U1", 10.5, "SUCCESS"),
    Transaction("U2", null, "SUCCESS"), // Invalid: Missing amount
    Transaction("U1", 20.0, "SUCCESS"),
    Transaction("U3", 100.0, "FAILED"), // Invalid: Failed transaction
    Transaction("U2", 50.0, "SUCCESS"),
    Transaction("U4", 30.0, "SUCCESS"),
    Transaction("U4", 30.0, "SUCCESS"),
    Transaction("U5", 5.0, "SUCCESS")
)

val k = 2

fun main() {
    logs.filter { it.amount != null && it.status == "SUCCESS" }
        .groupBy { it.userId }
        .map { (userId, transactions) ->
            userId to transactions.map { transaction -> transaction.amount!! }.sum()
        }
        .sortedBy { it.second }
        .reversed()
        .take(k)
        .forEach(::println)
}