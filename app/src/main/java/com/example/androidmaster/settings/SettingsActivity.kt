package com.example.androidmaster.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val SOUND_LVL = "sound_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATE = "key_vibrate"
        const val KEY_DARK_MODE = "key_dark_mode"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        //collect catch the fact which is update from the flow
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect{settingsModel->
                if (settingsModel!=null){
                    runOnUiThread {
                        binding.smVibration.isChecked=settingsModel.vibration
                        binding.smBluetooth.isChecked=settingsModel.bluetooth
                        binding.smDarkMode.isChecked=settingsModel.darkMode
                        binding.rsSound.setValues(settingsModel.sound.toFloat())
                        firstTime=!firstTime
                    }
                }
            }
        }

    }

    private fun initUI() {
        binding.rsSound.addOnChangeListener { _, value, _ ->
            Log.i("Paco", "The value is $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveSound(value.toInt())
            }
        }
        binding.smBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }
        binding.smVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATE, value)
            }
        }
        binding.smDarkMode.setOnCheckedChangeListener { _, value ->
            if (value){
                enableDarkMode()
            }else{
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }
        }
    }

    //This method save the sound when we close the app
    private suspend fun saveSound(value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(SOUND_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    //Flow update the facts of the database
    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map { preferences ->
            //if we don't use one of this settings, we initialize the options
            SettingsModel(
                sound = preferences[intPreferencesKey(SOUND_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATE)] ?: true
            )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}