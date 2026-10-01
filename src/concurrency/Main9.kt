package com.mycompany.myproject.concurrency

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class BankAccount {
    val mutex = Mutex()
    var balance = 0

    // THIS IS BROKEN!
    suspend fun deposit(amount: Int) {
        mutex.withLock {
            val current = balance
            delay(10) // Simulating network/database processing time
            balance = current + amount
        }
    }
}

suspend fun main() = coroutineScope {
    val account = BankAccount()

    // Launch 100 concurrent deposit tasks
    val jobs = List(100) {
        launch(Dispatchers.IO) {
            account.deposit(1)
        }
    }

    jobs.joinAll()
    println("Final Balance: ${account.balance}") // Should be 100, but won't be!
}