package com.mj.prizelottery.koin

import android.app.Application
import com.mj.prizelottery.koin.netWorkModule
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
//            androidContext(this@MainApplication)
            modules(netWorkModule())
//            modules(listOf(netWorkModule())) //여러개를 한번에 담을수도 있음
        }
    }
}