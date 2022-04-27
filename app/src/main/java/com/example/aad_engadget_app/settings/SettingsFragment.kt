package com.example.aad_engadget_app.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.aad_engadget_app.R

public class SettingsFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_general)
    }
}