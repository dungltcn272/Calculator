package com.dungltcn272.calculator.caculator.helper

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

}