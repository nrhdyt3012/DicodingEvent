package com.example.dicodingevent.ui.settings

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

// Membuat ekstensi dataStore untuk Context, nama "settings" adalah nama file penyimpanan DataStore
val Context.dataStore by preferencesDataStore(name = "settings")
