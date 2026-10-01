package com.mycompany.myproject.designpattern

sealed class PlayerState {
    data object Idle : PlayerState()
    // Both Playing and Paused need to hold data so the player remembers what is happening
    data class Playing(val trackName: String, val position: Int) : PlayerState()
    data class Paused(val trackName: String, val position: Int) : PlayerState()
}

class AudioPlayer {
    // 1. The player must have a memory of its current state!
    var currentState: PlayerState = PlayerState.Idle

    fun executeCommand(command: String, targetTrack: String = "") {
        when (command) {
            "PLAY" -> {
                val state = currentState
                // 2. We use 'when' as an expression to assign the new state
                currentState = when (state) {
                    is PlayerState.Idle -> {
                        println("Started playing $targetTrack")
                        PlayerState.Playing(targetTrack, 0)
                    }
                    is PlayerState.Paused -> {
                        println("Resumed ${state.trackName} from ${state.position}s")
                        PlayerState.Playing(state.trackName, state.position)
                    }
                    is PlayerState.Playing -> {
                        println("Already playing ${state.trackName}")
                        state // Return the existing state without changing it
                    }
                }
            }
            "PAUSE" -> {
                val state = currentState
                currentState = when (state) {
                    is PlayerState.Playing -> {
                        val newPos = state.position + 10
                        println("Paused ${state.trackName} at ${newPos}s")
                        PlayerState.Paused(state.trackName, newPos)
                    }
                    is PlayerState.Idle, is PlayerState.Paused -> {
                        println("Nothing to pause")
                        state
                    }
                }
            }
            "STOP" -> {
                println("Player stopped")
                currentState = PlayerState.Idle
            }
            else -> {
                println("Unknown command: $command")
            }
        }
    }
}

fun main() {
    val player = AudioPlayer()

    player.executeCommand("PLAY", "Bohemian Rhapsody")
    // Output: Started playing Bohemian Rhapsody

    player.executeCommand("PLAY")
    // Output: Already playing Bohemian Rhapsody

    player.executeCommand("PAUSE")
    // Output: Paused Bohemian Rhapsody at 10s

    player.executeCommand("PLAY")
    // Output: Resumed Bohemian Rhapsody from 10s

    player.executeCommand("STOP")
    // Output: Player stopped
}