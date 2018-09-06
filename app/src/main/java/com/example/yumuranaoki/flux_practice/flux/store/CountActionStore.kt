package com.example.yumuranaoki.flux_practice.flux.store

import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import com.example.yumuranaoki.flux_practice.flux.action.CountAction
import com.example.yumuranaoki.flux_practice.flux.action.Decrement
import com.example.yumuranaoki.flux_practice.flux.action.Increment
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class CountActionStore(dispatcher: Dispatcher<CountAction>) : Store<CountAction>(dispatcher) {
    // this is so-called state
    // 現在のcountもここで管理したい
    private val number: BehaviorSubject<Int> = BehaviorSubject.create()

    fun observeNumber(): Observable<Int> = number.hide()

    override fun handleAction(action: CountAction) {
        when (action) {
            is Increment -> {
                number.onNext(1)
            }
            is Decrement -> {
                number.onNext(-1)
            }
        }
    }

}