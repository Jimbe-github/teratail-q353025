package com.teratail.q353025;

import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> implements ColumnWeightManager.Receiver {
  private static class Record {
    final int id;
    final String menu, check, result, input, notices;

    Record(int id, String menu, String check, String result, String input, String notices) {
      this.id = id;
      this.menu = menu;
      this.check = check;
      this.result = result;
      this.input = input;
      this.notices = notices;
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView menu, check, result, input, notices;

    public ViewHolder(View view) {
      super(view);
      menu = view.findViewById(R.id.menu);
      check = view.findViewById(R.id.check);
      result = view.findViewById(R.id.result);
      input = view.findViewById(R.id.input);
      notices = view.findViewById(R.id.notices);
    }
  }

  RecordAdapter() {
    //テストデータ
    addRecord(new Record(1, "あ", "い", "う", "え", "お"));
    addRecord(new Record(2, "か", "き", "く", "け", "こ"));
    addRecord(new Record(3, "さ", "し", "す", "せ", "そ"));
    addRecord(new Record(4, "た", "ち", "つ", "て", "と"));
    addRecord(new Record(5, "な", "に", "ぬ", "ね", "の"));
    addRecord(new Record(6, "は", "ひ", "ふ", "へ", "ほ"));
    addRecord(new Record(7, "ま", "み", "む", "め", "も"));
    addRecord(new Record(8, "や", "ぃ", "ゆ", "ぇ", "よ"));
    addRecord(new Record(9, "ら", "り", "る", "れ", "ろ"));
    addRecord(new Record(10, "わ", "ぃ", "ぅ", "ぇ", "を"));
  }

  private List<Record> records = new ArrayList<>();
  private Map<Integer, Float> weights = new HashMap<>();

  void addRecord(Record r) {
    records.add(r);
  }

  @Override
  public void setWeight(Object obj, float weight) {
    int id = (Integer) obj;
    if(!weights.containsKey(id) || weights.get(id) != weight) {
      weights.put(id, weight);
      notifyItemRangeChanged(0, records.size());
    }
  }

  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Record r = records.get(position);
    setTextAndWeight(holder.menu, r.menu, weights.get(R.id.menu));
    setTextAndWeight(holder.check, r.check, weights.get(R.id.check));
    setTextAndWeight(holder.result, r.result, weights.get(R.id.result));
    setTextAndWeight(holder.input, r.input, weights.get(R.id.input));
    setTextAndWeight(holder.notices, r.notices, weights.get(R.id.notices));
    holder.notices.setVisibility(r.id % 3 == 0 ? View.INVISIBLE : View.VISIBLE);
  }

  private void setTextAndWeight(TextView view, String text, Float weight) {
    view.setText(text);

    if(weight != null) {
      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
      lp.weight = weight;
      view.setLayoutParams(lp);
    }
  }

  @Override
  public int getItemCount() {
    return records.size();
  }
}
