package com.example.yumuranaoki.flux_practice.flux.store

import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import io.reactivex.rxkotlin.subscribeBy

abstract class Store<Action : Any>(val dispatcher: Dispatcher<Action>) {
    init {
        dispatcher.observeDispatch()
                .subscribeBy(
                        onNext = ::handleAction
                )
    }

    abstract fun handleAction(action: Action)
}