package com.example.yumuranaoki.flux_practice.flux.actionCreator

import com.example.yumuranaoki.flux_practice.flux.Dispatcher

abstract class ActionCreator<Action>(val dispatcher: Dispatcher<Action>)