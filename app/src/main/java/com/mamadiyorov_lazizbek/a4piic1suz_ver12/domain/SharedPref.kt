package com.mamadiyorov_lazizbek.a4piic1suz_ver12.domain

import android.content.SharedPreferences

object SharedPref {
       private lateinit var sharedPreferences: SharedPreferences
       fun setSharedPref(sharedPreferences: SharedPreferences){
           this.sharedPreferences = sharedPreferences
       }

       fun getSharedPref() = sharedPreferences
   }
