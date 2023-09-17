package com.yusufnasserdev.todolist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Timber initialization
        Timber.plant(Timber.DebugTree())
    }
}