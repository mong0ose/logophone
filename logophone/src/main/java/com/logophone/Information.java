package com.logophone;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by mongoose on 25.02.14.
 */
public class Information extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_all);
        ListView lvAll = (ListView)findViewById(R.id.listOfInfoRows);
        Resources res = getResources();
        String[] rows = res.getStringArray(R.array.decode_elements_array);
        lvAll.setAdapter(new InfoListAdapter(this, rows));
    }
}
