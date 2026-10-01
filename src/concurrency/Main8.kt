package com.mycompany.myproject.concurrency

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

data class CartState(
    val items: List<String> = emptyList(),
    val totalItems: Int = 0
)

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    object NavigateToCheckout : UiEvent()
}


class CartManager {
    // Private and mutable - only CartManager can change this
    private val _state = MutableStateFlow(CartState())

    // Public and read-only - the UI listens to this
    val state: StateFlow<CartState> = _state.asStateFlow()

    fun addItem(itemName: String) {
        // this update method is thread safe
        _state.update { currentState ->
            currentState.copy(
                items = currentState.items + itemName,
                totalItems = currentState.totalItems + 1
            )
        }
    }
}

class EventManager {
    private val _channel = Channel<UiEvent>()
    val flow = _channel.receiveAsFlow()

    suspend fun triggerEvent(event: UiEvent) {
        _channel.send(event)
    }
}

suspend fun _main() = coroutineScope {
    val cartManager = CartManager()

    // 1. Launch the "UI Listener" in a background coroutine
    val uiJob = launch {
        // .collect will trigger immediately with the initial state,
        // and then every time the state changes.
        cartManager.state.collect { state ->
            println("UI Update -> Total Items: ${state.totalItems}, Cart contents: ${state.items}")
        }
    }

    // 2. Simulate user actions occurring over time
    println("User is browsing...")
    delay(1000.milliseconds) // Wait half a second

    println("User clicked 'Add Laptop'")
    cartManager.addItem("Laptop")

    delay(1000.milliseconds)
    println("User clicked 'Add Mouse'")
    cartManager.addItem("Mouse")

    delay(1000.milliseconds)
    println("User clicked 'Add Keyboard'")
    cartManager.addItem("Keyboard")

    // 3. Cleanup: Because .collect runs forever, we must cancel the listener to let the program exit.
    delay(1000.milliseconds)
    println("User left the screen. Canceling UI listener...")
    uiJob.cancel()
}

suspend fun main() = coroutineScope {
    val eventManager = EventManager()

    // 1. Launch the UI Listener
    val uiJob = launch {
        eventManager.flow.collect { event ->
            // 2. Pattern Matching! The compiler ensures we handle both cases.
            when (event) {
                is UiEvent.ShowToast -> {
                    println("📱 UI ACTION: Popping up a toast saying '${event.message}'")
                }
                is UiEvent.NavigateToCheckout -> {
                    println("📱 UI ACTION: Transitioning to the Checkout Screen ->")
                }
            }
        }
    }

    // 3. Simulate the ViewModel/Backend sending events
    println("User clicked 'Add to Cart'")
    eventManager.triggerEvent(UiEvent.ShowToast("Item successfully added!"))

    delay(500)

    println("User clicked 'Checkout'")
    eventManager.triggerEvent(UiEvent.NavigateToCheckout)

    // 4. Cleanup
    delay(500)
    uiJob.cancel()
}