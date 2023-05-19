package com.harang.gdsc_union

import android.app.Application
import android.content.Context

class GdscUnionApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: GdscUnionApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}