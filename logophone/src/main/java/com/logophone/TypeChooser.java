package com.logophone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by mongOose on 22.02.14.
 */
public class TypeChooser extends Activity {
    private Context mContext = this;
    private String[] typeToChoose = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private Spinner dSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_chooser);
        dSpinner = (Spinner) findViewById(R.id.spinnerChooserType);

        Button bdExit = (Button) findViewById(R.id.btnChooserBack);
        bdExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button bdSelect = (Button) findViewById(R.id.btnChooserSelect);
        bdSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int getTypeFromChooser = Integer.parseInt(String.valueOf(dSpinner.getSelectedItem().toString()));
                Intent myIntent = new Intent(mContext, Visualizer.class);
                myIntent.putExtra("intValType", getTypeFromChooser);
                startActivity(myIntent);
            }
        });
        ArrayAdapter<String> dSpinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, typeToChoose);
        dSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dSpinner.setAdapter(dSpinnerArrayAdapter);
    }
}
