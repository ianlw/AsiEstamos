package com.ianlw.asiestamos.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val DINERO_INICIAL = doublePreferencesKey("dinero_inicial")
        private val GEMINI_API_KEY = stringPreferencesKey("gemini_api_key")
        private val TEMA_SELECCIONADO = stringPreferencesKey("tema_seleccionado")
        private val MONEDA = stringPreferencesKey("moneda")
        private val SIMBOLO_MONEDA = stringPreferencesKey("simbolo_moneda")
    }

    val dineroInicial: Flow<Double> = context.dataStore.data.map { preferences ->
        preferences[DINERO_INICIAL] ?: 0.0
    }

    val geminiApiKey: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[GEMINI_API_KEY] ?: ""
    }

    val temaSeleccionado: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[TEMA_SELECCIONADO] ?: "LIQUID_GLASS"
    }

    val moneda: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[MONEDA] ?: "PEN"
    }

    val simboloMoneda: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[SIMBOLO_MONEDA] ?: "S/"
    }

    suspend fun setDineroInicial(monto: Double) {
        context.dataStore.edit { preferences ->
            preferences[DINERO_INICIAL] = monto
        }
    }

    suspend fun setGeminiApiKey(key: String) {
        context.dataStore.edit { preferences ->
            preferences[GEMINI_API_KEY] = key
        }
    }

    suspend fun setTemaSeleccionado(tema: String) {
        context.dataStore.edit { preferences ->
            preferences[TEMA_SELECCIONADO] = tema
        }
    }

    suspend fun setMoneda(moneda: String, simbolo: String) {
        context.dataStore.edit { preferences ->
            preferences[MONEDA] = moneda
            preferences[SIMBOLO_MONEDA] = simbolo
        }
    }
}
