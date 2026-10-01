package com.mycompany.myproject.concurrency

import kotlinx.coroutines.*
import java.io.IOException
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

data class UserProfile(val name: String)
data class UserPosts(val posts: List<String>)
data class Dashboard(val profile: UserProfile, val posts: UserPosts)

suspend fun fetchProfile(userId: String): UserProfile {
    delay(500.milliseconds)
    return UserProfile("Alice (ID: $userId)")
}

suspend fun fetchPosts(userId: String): UserPosts {
    delay(500.milliseconds)
    throw IOException("Network error: Failed to fetch posts")
}

// TODO: Change this to use supervisorScope, launch async tasks normally,
// and catch the exception when calling .await() for the posts.
suspend fun fetchDashboardSafe(userId: String): Dashboard = coroutineScope {
    val profileDeferred = async { fetchProfile(userId) }
    val postsDeferred = async {
        try {
            fetchPosts(userId)
        } catch (e: IOException) {
            UserPosts(emptyList())
        }
    }

    val profile = profileDeferred.await()

    // TODO: Use a try-catch around postsDeferred.await() to fall back to UserPosts(emptyList())
    val posts = postsDeferred.await()

    Dashboard(profile, posts)
}

class IOException(message: String) : Exception(message)

fun main() = runBlocking {
    val time = measureTimeMillis {
        val dashboard = fetchDashboardSafe("123")
        println("Dashboard: $dashboard")
    }
    println("Total time taken: ${time}ms")
}