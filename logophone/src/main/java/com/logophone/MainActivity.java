package com.logophone;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Git test!");

        Button bTable, bTest, bTraining, bVisualizer, bConstructor, bExit;
        bVisualizer = (Button)findViewById(R.id.btnLearn);
        bVisualizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Visualizer.class));
            }
        });
        bTraining = (Button) findViewById(R.id.btnTraining);
        bTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), TrainingPart.class));
            }
        });
        bTest = (Button)findViewById(R.id.btnTest);
        bTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), TestingGame.class));
//                Intent iPick = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//                startActivityForResult(iPick, PICK_CONTACT);
            }
        });
        bTable = (Button)findViewById(R.id.btnTable);
        bTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), TableLayoutFixed.class));
            }
        });
        bConstructor = (Button)findViewById(R.id.btnConstruct);
        bConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), Constructor.class));
            }
        });
        bExit = (Button)findViewById(R.id.btnExit);
        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
