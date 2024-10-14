package com.example.lab6.task8

import android.content.Context

class ConfigurationGroup(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("app_config", Context.MODE_PRIVATE)

    var textValue: String
        get() = sharedPreferences.getString("text_value", "") ?: ""
        set(value) {
            sharedPreferences.edit().putString("text_value", value).apply()
        }

    var checkboxValue: Boolean
        get() = sharedPreferences.getBoolean("checkbox_value", false)
        set(value) {
            sharedPreferences.edit().putBoolean("checkbox_value", value).apply()
        }
}
