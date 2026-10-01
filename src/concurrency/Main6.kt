package com.mycompany.myproject.structuredconcurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

data class User(val id: String, val name: String)
data class Preferences(val receivesNewsletter: Boolean)
data class DashboardData(val user: User, val preferences: Preferences)


suspend fun fetchUser(userId : String): User {
    delay(1000)
    return User("1XX", "John Smith")
}


suspend fun fetchPreferences(userId : String): Preferences {
    delay(1000)
    return Preferences(true)
}

suspend fun getDashboardData(userId: String): DashboardData {
    val dashboardData = withContext(Dispatchers.IO) {

        // 2. Start both tasks concurrently using 'async'
        val deferredUser = async { fetchUser(userId) }
        val deferredPrefs = async { fetchPreferences(userId) }

        // 3. Suspend until BOTH tasks are complete, and unwrap their values
        val user = deferredUser.await()
        val prefs = deferredPrefs.await()

        // 4. Return the aggregated result
        DashboardData(user, prefs)
    }
    return dashboardData
}

suspend fun main() {
    // We can drop the `run {}` block, `suspend fun main` handles suspending naturally!
    val timeTaken = measureTimeMillis {
        val dashboard = getDashboardData("1XX")
        println(dashboard)
    }

    println("Execution time: $timeTaken ms")
    // Output will be around ~1020 ms, proving they ran at the same time!
}