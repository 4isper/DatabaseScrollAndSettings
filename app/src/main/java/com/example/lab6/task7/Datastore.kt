package com.example.lab6.task7

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

object PreferencesKeys {
    val TEXT_VALUE = stringPreferencesKey("text_value")
    val CHECKBOX_VALUE = booleanPreferencesKey("checkbox_value")
}

suspend fun saveToPreferences(context: Context, text: String, checkbox: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[PreferencesKeys.TEXT_VALUE] = text
        preferences[PreferencesKeys.CHECKBOX_VALUE] = checkbox
    }
}

fun getTextFromPreferences(context: Context): Flow<String> {
    return context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.TEXT_VALUE] ?: ""
    }
}

fun getCheckboxFromPreferences(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.CHECKBOX_VALUE] ?: false
    }
}
