package com.example.yumuranaoki.flux_practice.flux.actionCreator

import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import com.example.yumuranaoki.flux_practice.flux.action.CountAction
import com.example.yumuranaoki.flux_practice.flux.action.Decrement
import com.example.yumuranaoki.flux_practice.flux.action.Increment

class CountActionCreator(dispatcher: Dispatcher<CountAction>) : ActionCreator<CountAction>(dispatcher) {
    fun increment() = dispatcher.dispatch(Increment())
    fun decrement() = dispatcher.dispatch(Decrement())
}