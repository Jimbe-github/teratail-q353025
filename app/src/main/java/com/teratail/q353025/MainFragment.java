package com.teratail.q353025;

import android.content.*;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.*;

import android.view.*;
import android.widget.*;

public class MainFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    ColumnWeightManager.Receiver headerWeightReceiver = (obj, weight) -> {
      View v = (View)obj;
      LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)v.getLayoutParams();
      lp.weight = weight;
      v.setLayoutParams(lp);
    };

    //Preference の key と各 View の関係
    Object data[][] = {
            {"column.weight.menu",   R.id.header_menu,   R.id.menu   },
            {"column.weight.check",  R.id.header_check,  R.id.check  },
            {"column.weight.result", R.id.header_result, R.id.result },
            {"column.weight.input",  R.id.header_input,  R.id.input  },
            {"column.weight.notices",R.id.header_notices,R.id.notices},
    };
    for(Object obj[] : data) {
      cwmanager.putReceiver((String)obj[0], headerWeightReceiver, view.findViewById((Integer)obj[1]));
      cwmanager.putReceiver((String)obj[0], adapter, obj[2]);
    }

    RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
    RecyclerView.ItemDecoration ideco = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(ideco);
    recyclerView.setAdapter(adapter);
  }

  private RecordAdapter adapter = new RecordAdapter();
  private ColumnWeightManager cwmanager = new ColumnWeightManager();

  @Override
  public void onResume() {
    super.onResume();

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    cwmanager.distributeWeight(prefs);
  }
}