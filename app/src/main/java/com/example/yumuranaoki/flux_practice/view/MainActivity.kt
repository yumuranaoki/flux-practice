package com.example.yumuranaoki.flux_practice.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.yumuranaoki.flux_practice.R
import com.example.yumuranaoki.flux_practice.contract.CountViewContract
import com.example.yumuranaoki.flux_practice.databinding.ActivityMainBinding
import com.example.yumuranaoki.flux_practice.di.DaggerCountComponent
import com.example.yumuranaoki.flux_practice.flux.Dispatcher
import com.example.yumuranaoki.flux_practice.flux.action.CountAction
import com.example.yumuranaoki.flux_practice.flux.actionCreator.CountActionCreator
import com.example.yumuranaoki.flux_practice.flux.store.CountStore
import com.example.yumuranaoki.flux_practice.viewModel.CountViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CountViewContract {
    var disposable : Disposable? = null
    @Inject lateinit var dispatcher: Dispatcher<CountAction>

    @Inject lateinit var countStore: CountStore

    @Inject lateinit var countActionCreator: CountActionCreator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerCountComponent.create().inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = CountViewModel(this, countActionCreator)

        val countText: TextView = findViewById(R.id.count)
        countStore.observeNumber()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = ::changeNumber
                )
        countText.text = countStore.numberState.toString()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun changeNumber(number: Int) {
        val countText: TextView = findViewById(R.id.count)
        countText.text = number.toString()
    }
}
