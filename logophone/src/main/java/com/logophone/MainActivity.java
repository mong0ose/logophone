package com.logophone;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    private static final int D_INFO = 1;
//    private static final int RQ_CODE = 31172;
    private Context mContext = this;
//    private String folder;
    private int[] colors_array = {
            Color.rgb(245, 245, 245),           // WHITE
            Color.rgb(255, 1, 1),               // RED
            Color.rgb(255, 153, 51),            // ORANGE
            Color.rgb(225, 225, 35),            // YELLOW
            Color.rgb(5, 175, 5),               // GREEN
            Color.rgb(140, 190, 252),           // SKY-BLUE
            Color.rgb(55, 55, 225),             // BLUE
            Color.rgb(182, 29, 142),            // PURPLE
            Color.rgb(186, 114, 41),            // BROWN
            Color.rgb(1, 1, 1)                  // BLACK
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final File folder = new File(Environment.getExternalStorageDirectory() + "/LogophoneContacts");
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdir();
        }

        Button bTable, bTest, bTraining, bVisualizer, bConstructor, bInfo, bExit, bGallery, bModes;
        bGallery = (Button)findViewById(R.id.btnGallery);
        bGallery.getBackground().setColorFilter(colors_array[6], PorterDuff.Mode.MULTIPLY);
        bGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(folder.exists() && folder.list().length > 0){
                    startActivity(new Intent(mContext, GridSavedContactsViewActivity.class));
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                } else
                    Toast.makeText(mContext, "Gallery is empty!", Toast.LENGTH_SHORT).show();
            }
        });
        bModes = (Button) findViewById(R.id.btnTypeChooser);
        bModes.getBackground().setColorFilter(colors_array[4], PorterDuff.Mode.MULTIPLY);
        bModes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, GameModeChooser.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        bTable = (Button)findViewById(R.id.btnTable);
        bTable.getBackground().setColorFilter(colors_array[3], PorterDuff.Mode.MULTIPLY);
        bTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, TableLayoutFixed.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        bConstructor = (Button)findViewById(R.id.btnConstruct);
        bConstructor.getBackground().setColorFilter(colors_array[1], PorterDuff.Mode.MULTIPLY);
        bConstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Constructor.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        bInfo = (Button)findViewById(R.id.btnInfo);
        bInfo.getBackground().setColorFilter(colors_array[2], PorterDuff.Mode.MULTIPLY);
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
                overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
            }
        });
    }

    private Dialog DialogManager(int dType){
        final Dialog dialog = new Dialog(mContext, R.style.myBackgroundStyle);
        switch (dType){
            case D_INFO:
                dialog.setContentView(R.layout.information_all);
//                dialog.getWindow().setBackgroundDrawable(null);
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
//
//    private String getRealPathFromURI(Uri contentURI) {
//        Cursor cursor = getContentResolver()
//                .query(contentURI, null, null, null, null);
//        cursor.moveToFirst();
//        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//        return cursor.getString(idx);
//    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
}
