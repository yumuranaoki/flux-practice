package com.example.yumuranaoki.flux_practice.viewModel

import com.example.yumuranaoki.flux_practice.contract.CountViewContract
import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import com.example.yumuranaoki.flux_practice.flux.action.CountAction
import com.example.yumuranaoki.flux_practice.flux.actionCreator.CountActionCreator

class CountViewModel(val countView: CountViewContract, val countActionCreator: CountActionCreator) {
    fun onClickIncButton() {
        countActionCreator.increment()
    }

    fun onClickDecButton() {
        countActionCreator.decrement()
    }
}
