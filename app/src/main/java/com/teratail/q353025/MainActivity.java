package com.teratail.q353025;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.*;

import android.content.*;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    //prefs.edit().clear().apply();

    PreferenceManager.setDefaultValues(this, R.xml.column_weight_preferences, false);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id == R.id.action_settings) {
      Intent act = new Intent(this, PreferenceActivity.class);
      startActivity(act);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}