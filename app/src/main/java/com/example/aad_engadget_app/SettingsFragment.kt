package com.example.aad_engadget_app

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

public class SettingsFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_general)
    }
}