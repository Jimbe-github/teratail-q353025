package com.teratail.q353025;

import android.content.SharedPreferences;

import java.util.*;

/**
 * Preference の設定値をレシーバに配送する.
 */
class ColumnWeightManager {
  public interface Receiver {
    void setWeight(Object obj, float weight);
  }

  private class Info {
    final Receiver receiver;
    final Object obj;
    Info(Receiver receiver, Object obj) {
      this.receiver = receiver;
      this.obj = obj;
    }
  }
  private Map<String,List<Info>> distributeInfo = new HashMap<>();

  void putReceiver(String prefKey, Receiver receiver, Object obj) {
    List<Info> value = null;
    if(distributeInfo.containsKey(prefKey)) {
      value = distributeInfo.get(prefKey);
    } else {
      value = new ArrayList<>();
      distributeInfo.put(prefKey, value);
    }
    value.add(new Info(receiver, obj));
  }

  void distributeWeight(SharedPreferences prefs) {
    for(String prefKey : distributeInfo.keySet()) {
      float value = prefs.getInt(prefKey, 1);
      for(Info info : distributeInfo.get(prefKey)) {
        info.receiver.setWeight(info.obj, value);
      }
    }
  }
}
