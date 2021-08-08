package com.teratail.q353025;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class ColumnWeightPrefFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    setPreferencesFromResource(R.xml.column_weight_preferences, rootKey);
  }
}