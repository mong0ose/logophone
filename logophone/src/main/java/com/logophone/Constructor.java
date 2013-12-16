package com.logophone;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mongOose on 11.11.13.
 */
public class Constructor extends Activity {
    private Bitmap bMap;
    private static final int DIALOG_CONTACTS = 0;
    private static final int DIALOG_PHONES = 1;
    private Context mContext = this;
    private ArrayAdapter contacts_array;
    private ArrayAdapter phones_array;
    private int[] colors_array = {
            Color.WHITE,
            Color.RED,
            Color.rgb(255, 165, 0), // #FF8000
            Color.YELLOW,
            Color.GREEN,
            Color.rgb(0, 184, 217), // #00B8D9
            Color.BLUE,
            Color.rgb(90, 0, 157),  // #5A009D
            Color.rgb(150, 75, 0),  // #964B00
            Color.BLACK};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_logo_by_number);

        final ImageView image;
        int phone_number[] = {0,6,7,4,6,8,5,4,9,6};

        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point p = new Point();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);
        System.out.println("Screen resolution: " + p.x + "x" + p.y);
        Bitmap bmapOverlay;
        bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmapOverlay);

        Button bGet = (Button)findViewById(R.id.btnConstContacts);
        bGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);
                ArrayList<String> mArrayList = new ArrayList<String>();
                try {
                    if (cur != null && cur.getCount() > 0) {
                        for(cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                            String name = cur.getString(
                                    cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER))) > 0) {
                                mArrayList.add(name);
                            }
                        }
                        contacts_array = new ArrayAdapter(mContext, android.R.layout.simple_list_item_single_choice, mArrayList);
                        DialogManager(DIALOG_CONTACTS);
                    }
                }finally {
                    cur.close();
                }
            }
        });
        EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);
        et5 = (EditText)findViewById(R.id.editText5);
        et6 = (EditText)findViewById(R.id.editText6);
        et7 = (EditText)findViewById(R.id.editText7);
        et8 = (EditText)findViewById(R.id.editText8);
        et9 = (EditText)findViewById(R.id.editText9);
        et10 = (EditText)findViewById(R.id.editText10);

        if(phone_number.length != 0){
            et1.setText(String.valueOf(phone_number[0]));
            et2.setText(String.valueOf(phone_number[1]));
            et3.setText(String.valueOf(phone_number[2]));
            et4.setText(String.valueOf(phone_number[3]));
            et5.setText(String.valueOf(phone_number[4]));
            et6.setText(String.valueOf(phone_number[5]));
            et7.setText(String.valueOf(phone_number[6]));
            et8.setText(String.valueOf(phone_number[7]));
            et9.setText(String.valueOf(phone_number[8]));
            et10.setText(String.valueOf(phone_number[9]));
        }

        String[] filename = new String[10];
        Creator check = new Creator();
        int overlap = check.chechForOverlap(phone_number);
        int overlap_3rd = check.checkForOverlap_3rd(phone_number);
        Boolean isStrict = check.checkForStrict(phone_number);

//_______________________CHARACTER__________________________________________________________________
        filename[3] = phone_number[3] + "XXXXXXX.png";
        addLayoutToCanvas(filename[3], canvas, p);

//_______________________FIRST_LAY__________________________________________________________________
        if(overlap == 1 && !isStrict){
            if(phone_number[5] == 6 || phone_number[5] == 7)
                filename[5] = phone_number[3] + "" + phone_number[7] + "1" + phone_number[6] + "XXXX.png";  //застегнут
            else
                filename[5] = phone_number[3] + "" + phone_number[7] + "0" + phone_number[6] + "XXXX.png";  //растегнута
        } else {
            if(phone_number[5] == 6 || phone_number[5] == 7)
                filename[5] = phone_number[3] + "" + phone_number[5] + "1" + phone_number[4] + "XXXX.png";          //застегнут
            else
                filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4] + "XXXX.png";          //рпстегнут
        }
        addLayoutToCanvas(filename[5], canvas, p);

//_______________________SECOND_LAY_________________________________________________________________
        if(isStrict){
            // с узорами
            filename[7] = phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "XX.png"; //одежда
            addLayoutToCanvas(filename[7], canvas, p);

//_______________________GLASSES_OR_LAY_____________________________________________________________
            if(check.checkForGlasses(phone_number)){
                filename[9] = phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";              //очки

                addLayoutToCanvas(filename[9], canvas, p);
            } else {
                if(overlap_3rd == 1)
                    filename[9] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4] + "XXXX.png";  //одежда
                else if(overlap_3rd ==2)
                    filename[9] = phone_number[3] + "" + phone_number[7] + "0" + phone_number[6] + "XXXX.png";  //одежда
                else if(overlap_3rd == 3 || overlap_3rd == 0)
                    filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8] + "XXXX.png";  //одежда
                addLayoutToCanvas(filename[9], canvas, p);
            }

//_______________________SECOND_LAY_________________________________________________________________
        } else {
            if(overlap == 1){
                if (phone_number[7] == 6 || phone_number[7] == 7)
                    filename[7] = phone_number[3] + "" + phone_number[5] + "1" + phone_number[4] + "XXXX.png";  //застегнута
                else
                    filename[7] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4] + "XXXX.png";  //растегнут
            } else {
                if (phone_number[7] == 6 || phone_number[7] == 7)
                    filename[7] = phone_number[3] + "" + phone_number[7] + "1" + phone_number[6] + "XXXX.png"; //застегнута
                else
                    filename[7] = phone_number[3] + "" + phone_number[7] + "0" + phone_number[6] + "XXXX.png"; //растегнута
            }
            addLayoutToCanvas(filename[7], canvas, p);

//_______________________GLASSES_OR_LAY_____________________________________________________________
            if(check.checkForGlasses(phone_number)){
                filename[9] = phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";              //очки

                addLayoutToCanvas(filename[9], canvas, p);
            } else {
                if(overlap_3rd == 1)
                    filename[9] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4] + "XXXX.png";  //одежда
                else if(overlap_3rd ==2)
                    filename[9] = phone_number[3] + "" + phone_number[7] + "0" + phone_number[6] + "XXXX.png";  //одежда
                else if(overlap_3rd == 3 || overlap_3rd == 0)
                    filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8] + "XXXX.png";  //одежда

                addLayoutToCanvas(filename[9], canvas, p);
            }
        }
        System.out.println(filename[3] + "\n" + filename[5] + "\n" + filename[7] + "\n" + filename[9] + "\n");

        image = (ImageView)findViewById(R.id.imgCreateLogo);
        image.setImageBitmap(bmapOverlay);
    }

    private int[] convert(String[] string) {
        int number[] = new int[string.length];

        for (int i = 0; i < string.length; i++) {
            number[i] = Integer.parseInt(string[i]);
        }
        return number;
    }

    private Bitmap resizeBitmap(Bitmap bMap, int w, int h, int searchColor, int replaceColor){
        Bitmap rbMap = Bitmap.createScaledBitmap(bMap, w, h, false);

        bMap.recycle();
        if(replaceColor != 0){
            rbMap.copy(Bitmap.Config.ARGB_8888, true);
            int lenght = rbMap.getWidth()*rbMap.getHeight();
            int[] pixels = new int[lenght];
            rbMap.getPixels(pixels, 0, rbMap.getWidth(), 0, 0, rbMap.getWidth(), rbMap.getHeight());
            for(int i=0; i < lenght; ++i){
                int y = i/rbMap.getWidth();
                int x = i - (y*rbMap.getWidth());
                int pixel = rbMap.getPixel(x, y);
                if(pixel == searchColor){
                    rbMap.setPixel(x, y, replaceColor);
                } else if(pixel == replaceColor){
                    rbMap.setPixel(x, y, searchColor);
                }

            }
        }
        return rbMap;
    }

    private void addLayoutToCanvas(String filename, Canvas canvas, Point p){
        try {
            InputStream is = getAssets().open("raw/" + filename);
            bMap = BitmapFactory.decodeStream(is);
            is.close();
            canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void DialogManager(int D_ID){
        final Dialog dialog = new Dialog(mContext);

        switch (D_ID){
            case DIALOG_CONTACTS:
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.list_contacts);
                Button back_contacts = (Button)dialog.findViewById(R.id.btnBackListContacts);
                back_contacts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ListView listContacts = (ListView)dialog.findViewById(R.id.listViewContacts);

                listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<String> phones = new ArrayList<String>();
                        Cursor curs = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=\"" + adapterView.getItemAtPosition(i) + "\"", null, null);
                        try{
                            if(curs != null && curs.getCount() > 0){
                                curs.moveToFirst();
                                if (Integer.parseInt(curs.getString(curs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER))) > 0) {
                                    do{
                                        String number = curs.getString(curs.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        System.out.println(number);
                                        phones.add(number);
                                    }while(curs.moveToNext());
                                    phones_array = new ArrayAdapter(mContext, android.R.layout.simple_list_item_single_choice, phones);
                                    DialogManager(DIALOG_PHONES);
                                }
                            }
                        }finally {
                            curs.close();
                        }
                    }
                });
                listContacts.setAdapter(contacts_array);
                dialog.show();
                break;
            case DIALOG_PHONES:
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.list_phones);
                Button back_phones = (Button)dialog.findViewById(R.id.btnBackListPhones);
                back_phones.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ListView listPhones = (ListView)dialog.findViewById(R.id.listViewPhones);

                listPhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                listPhones.setAdapter(phones_array);
                dialog.show();
                break;
            default:
                break;
        }
    }
}
