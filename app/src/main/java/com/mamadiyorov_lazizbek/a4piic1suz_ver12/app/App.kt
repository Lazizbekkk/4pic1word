package com.mamadiyorov_lazizbek.a4piic1suz_ver12.app

import android.app.Application
import android.content.Context
import com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain.SharedPref

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.setSharedPref(this.getSharedPreferences("Lazizbek", Context.MODE_PRIVATE))
    }
}