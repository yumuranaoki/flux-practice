package com.example.yumuranaoki.flux_practice.flux.action

sealed class CountAction

class Increment : CountAction()
class Decrement : CountAction()
