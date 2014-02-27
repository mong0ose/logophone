package com.logophone;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{
    private static final int D_INFO = 1;
    private static final int RQ_CODE = 31172;
    private Context mContext = this;
    private int[] colors_array = {
            Color.rgb(245, 245, 245),           // WHITE
            Color.rgb(255, 1, 1),               // RED
            Color.rgb(255, 153, 51),            // ORANGE
            Color.rgb(255, 255, 1),             // YELLOW
            Color.rgb(5, 233, 5),               // GREEN
            Color.rgb(140, 190, 252),           // SKY-BLUE
            Color.rgb(1, 1, 255),               // BLUE
            Color.rgb(182, 29, 142),            // PURPLE
            Color.rgb(186, 114, 41),            // BROWN
            Color.rgb(1, 1, 1)                  // BLACK
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bTable, bTest, bTraining, bVisualizer, bConstructor, bInfo, bExit, bGallery;
        bGallery = (Button)findViewById(R.id.btnGallery);
        bGallery.getBackground().setColorFilter(colors_array[1], PorterDuff.Mode.MULTIPLY);
        bGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://"));
//
//                startActivityForResult(intent, RQ_CODE);

                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().build();
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        bVisualizer = (Button)findViewById(R.id.btnLearn);
        bVisualizer.getBackground().setColorFilter(colors_array[8], PorterDuff.Mode.MULTIPLY);
        bVisualizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Visualizer.class));
//                startActivity(new Intent(mContext, TypeChooser.class));
            }
        });
        bTraining = (Button) findViewById(R.id.btnTraining);
        bTraining.getBackground().setColorFilter(colors_array[2], PorterDuff.Mode.MULTIPLY);
        bTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, TrainingPart.class));
//                startActivity(new Intent(mContext, TrainingTypeChooser.class));
            }
        });
        bTest = (Button)findViewById(R.id.btnTest);
        bTest.getBackground().setColorFilter(colors_array[3], PorterDuff.Mode.MULTIPLY);
        bTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, TestingGame.class));
            }
        });
        bTable = (Button)findViewById(R.id.btnTable);
        bTable.getBackground().setColorFilter(colors_array[4], PorterDuff.Mode.MULTIPLY);
        bTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, TableLayoutFixed.class));
            }
        });
        bConstructor = (Button)findViewById(R.id.btnConstruct);
        bConstructor.getBackground().setColorFilter(colors_array[5], PorterDuff.Mode.MULTIPLY);
        bConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Constructor.class));
            }
        });
        bInfo = (Button)findViewById(R.id.btnInfo);
        bInfo.getBackground().setColorFilter(colors_array[6], PorterDuff.Mode.MULTIPLY);
        bInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogManager(D_INFO);
//                startActivity(new Intent(mContext, Information.class));
            }
        });
        bExit = (Button)findViewById(R.id.btnExit);
        bExit.getBackground().setColorFilter(colors_array[7], PorterDuff.Mode.MULTIPLY);
        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private Dialog DialogManager(int dType){
        final Dialog dialog = new Dialog(mContext);
        switch (dType){
            case D_INFO:
                dialog.setContentView(R.layout.information_all);
                dialog.setTitle("Information:");
                dialog.setCanceledOnTouchOutside(true);
                Button bOk = (Button)dialog.findViewById(R.id.btnOk);
                bOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ListView lvAll = (ListView) dialog.findViewById(R.id.listOfInfoRows);
                Resources res = getResources();
                String[] rows = res.getStringArray(R.array.decode_elements_array);
                lvAll.setAdapter(new InfoListAdapter(mContext, rows));

                dialog.show();
                break;
            default:
                break;
        }
        return null;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
