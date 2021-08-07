package com.teratail.q353025;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    RecyclerView.ItemDecoration ideco = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(ideco);

    adapter = new RecordAdapter();
    adapter.addRecord(new Record(1,"あ","い","う","え","お"));
    adapter.addRecord(new Record(2,"か","き","く","け","こ"));
    adapter.addRecord(new Record(3,"さ","し","す","せ","そ"));
    adapter.addRecord(new Record(4,"た","ち","つ","て","と"));
    adapter.addRecord(new Record(5,"な","に","ぬ","ね","の"));
    adapter.addRecord(new Record(6,"は","ひ","ふ","へ","ほ"));
    adapter.addRecord(new Record(7,"ま","み","む","め","も"));
    adapter.addRecord(new Record(8,"や","ぃ","ゆ","ぇ","よ"));
    adapter.addRecord(new Record(9,"ら","り","る","れ","ろ"));
    adapter.addRecord(new Record(10,"わ","ぃ","ぅ","ぇ","を"));
    recyclerView.setAdapter(adapter);

    setWeightChangeListener(R.id.menu_weight, R.id.header_menu, R.id.menu);
    setWeightChangeListener(R.id.check_weight, R.id.header_check, R.id.check);
    setWeightChangeListener(R.id.result_weight, R.id.header_result, R.id.result);
    setWeightChangeListener(R.id.input_weight, R.id.header_input, R.id.input);
    setWeightChangeListener(R.id.notices_weight, R.id.header_notices, R.id.notices);
  }

  private void setWeightChangeListener(int editTextId, int headerId, int lineTextId) {
    ((EditText)findViewById(editTextId)).addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
      @Override
      public void afterTextChanged(Editable editable) {
        String s = editable.toString();
        if(s == null || s.isEmpty()) return;
        int weight = Integer.valueOf(s);
        setWeight(findViewById(headerId), weight);
        adapter.setWeight(lineTextId, weight);
      }
    });
  }

  private void setWeight(View view, float weight) {
    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)view.getLayoutParams();
    lp.weight = weight;
    view.setLayoutParams(lp);
  }

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

  private RecordAdapter adapter;

  private static class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
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
      weights.put(R.id.menu, 20f);
      weights.put(R.id.check, 25f);
      weights.put(R.id.result, 7f);
      weights.put(R.id.input, 13f);
      weights.put(R.id.notices, 35f);
    }

    private List<Record> records = new ArrayList<>();
    private Map<Integer,Float> weights = new HashMap<>();

    void addRecord(Record r) {
      records.add(r);
    }

    void setWeight(int id, float weight) {
      if(weights.get(id) != weight) {
        weights.put(id, weight);
        notifyItemRangeChanged(0, records.size());
      }
    }

    private void setWeight(View view, float weight) {
      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)view.getLayoutParams();
      lp.weight = weight;
      view.setLayoutParams(lp);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.record_layout, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Record r = records.get(position);
      holder.menu.setText(r.menu);
      setWeight(holder.menu, weights.get(R.id.menu));
      holder.check.setText(r.check);
      setWeight(holder.check, weights.get(R.id.check));
      holder.result.setText(r.result);
      setWeight(holder.result, weights.get(R.id.result));
      holder.input.setText(r.input);
      setWeight(holder.input, weights.get(R.id.input));
      holder.notices.setText(r.notices);
      setWeight(holder.notices, weights.get(R.id.notices));
      holder.notices.setVisibility(r.id%3 == 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
      return records.size();
    }
  }
}