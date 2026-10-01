package com.mycompany.myproject.structuredconcurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

suspend fun uploadPendingAnalytics() {
    delay(1_000.milliseconds)
    println("Analytics uploaded.")
}

suspend fun clearLocalDatabase() {
    delay(1_200.milliseconds)
    println("Local database cleared.")
}

suspend fun revokeAuthToken() {
    delay(800.milliseconds)
    println("Auth token revoked.")
}

suspend fun performSecureLogout() {
    coroutineScope {
        val job1 = launch {
            uploadPendingAnalytics()
        }
        val job2 = launch {
            clearLocalDatabase()
        }
        val job3 = launch {
            revokeAuthToken()
        }
        joinAll(job1, job2, job3)
        println("User safely logged out.")
    }
}


suspend fun main() {
    val timeTaken = measureTimeMillis {
        performSecureLogout()
    }
    println("$timeTaken ms")
}