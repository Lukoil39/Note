package com.rojer_ko.notes

import androidx.multidex.MultiDexApplication
import com.rojer_ko.notes.di.*
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, splasModule, mainModule, noteModule))
    }
}