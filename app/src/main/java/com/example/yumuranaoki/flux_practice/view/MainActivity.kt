package com.example.yumuranaoki.flux_practice.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.yumuranaoki.flux_practice.R
import com.example.yumuranaoki.flux_practice.contract.CountViewContract
import com.example.yumuranaoki.flux_practice.databinding.ActivityMainBinding
import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import com.example.yumuranaoki.flux_practice.flux.action.CountAction
import com.example.yumuranaoki.flux_practice.flux.store.CountActionStore
import com.example.yumuranaoki.flux_practice.viewModel.CountViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class MainActivity : AppCompatActivity(), CountViewContract {
    var disposable : Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dispatcher = Dispatcher<CountAction>()
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = CountViewModel(this, dispatcher)
        val countActionStore = CountActionStore(dispatcher)
        countActionStore.observeNumber()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = ::changeNumber
                )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun changeNumber(number: Int) {
        val countText = findViewById<TextView>(R.id.count)
        val count = countText.text.toString().toInt()
        val newCount = count + number
        countText.text = newCount.toString()
    }
}
