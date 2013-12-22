package com.logophone;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mongOose on 11.11.13.
 */
public class Constructor extends Activity {
    private Bitmap bMap;
    private ProgressDialog mProgressDialog;
    private static final int DIALOG_CONTACTS = 0;
    private static final int DIALOG_PHONES = 1;
    private Context mContext = this;
    private ArrayAdapter contacts_array;
    private ArrayAdapter phones_array;
    private int[] colors_array = {
            Color.rgb(245, 245, 245),   //WHITE
            Color.RED,
            Color.rgb(255, 165, 0),     // #FF8000
            Color.YELLOW,
            Color.GREEN,
            Color.rgb(0, 184, 217),     // #00B8D9
            Color.BLUE,
            Color.rgb(90, 0, 157),      // #5A009D
            Color.rgb(150, 75, 0),      // #964B00
            Color.rgb(2, 2, 2)          //BLACK
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_logo_by_number);

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

    }


    private Bitmap resizeBitmap(Bitmap bbMap, int w, int h, int searchColor, int replaceColor){
        Bitmap rbMap = Bitmap.createScaledBitmap(bbMap, w, h, false);

        bbMap.recycle();
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

    private void addLayoutToCanvas(String filename, Canvas canvas, Point p, int color){
        try {
            InputStream is = getAssets().open("raw/" + filename);
            bMap = BitmapFactory.decodeStream(is);
            is.close();
            canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, colors_array[9], color), new Matrix(), null);
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
                                    dialog.dismiss();
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
                        DialogManager(DIALOG_CONTACTS);
                    }
                });
                ListView listPhones = (ListView)dialog.findViewById(R.id.listViewPhones);

                listPhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mProgressDialog = new ProgressDialog(view.getContext());
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.setMessage("Создание логотипа...");
                        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        mProgressDialog.show();
                        String phone = (String) phones_array.getItem(i);

                        new CreateImageFromContact().execute(convert(phone.replace("-", "")));
                        dialog.dismiss();
                    }
                });
                listPhones.setAdapter(phones_array);
                dialog.show();
                break;
            default:
                break;
        }
    }

    private Integer[] convert(String string) {
        char[] replace;
        if(string.length() > 10){
            replace = string.substring(string.length() - 10).toCharArray();
        } else {
            replace = string.toCharArray();
        }
        Integer number[] = new Integer[10];

        for (int i = 0; i < 10; i++) {
            number[i] = Integer.parseInt(String.valueOf(replace[i]));
        }
        return number;
    }

    private class CreateImageFromContact extends AsyncTask<Integer, Integer, Boolean>{
        private ImageView image;
        private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
        private Integer phone_number[] = {};
        private Bitmap bmapOverlay;

        @Override
        protected void onPostExecute(Boolean doCreate) {
            super.onPostExecute(doCreate);
            mProgressDialog.dismiss();
            if(doCreate){
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

                    image.setImageBitmap(bmapOverlay);
                }
            }
        }

        @Override
        protected Boolean doInBackground(Integer... ints) {
            phone_number = ints;
            Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point p = new Point();
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
                p.x = disp.getWidth();
                p.y = disp.getHeight();
            } else
                disp.getSize(p);

            bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmapOverlay);

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

            String[] filename = new String[10];
            Creator check = new Creator();
            int overlap = check.chechForOverlap(phone_number);
            int overlap3rd = check.checkForOverlap_3rd(phone_number);
            int galstuk = check.checkGalstuk(phone_number);
            int isLower = check.checkLower(phone_number);
            Boolean isGlasses = check.checkForGlasses(phone_number);
            Boolean isStrict = check.checkForStrict(phone_number);

//_______________________CHARACTER__________________________________________________________________
            filename[3] = phone_number[3] + "XXXXXXX.png";
            addLayoutToCanvas(filename[3], canvas, p, 0);

//_______________________FIRST_LAY__________________________________________________________________
            if((phone_number[5] == 6 || phone_number[5] == 7) && isLower == 2
                    || phone_number[5] == 6 && galstuk == 1 && phone_number[5] != 4 && phone_number[5] != 2)
                filename[5] = phone_number[3] + "" + phone_number[5] + "1" + phone_number[4];          //застегнут
            else
                filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];          //рпстегнут

//_______________________SECOND_LAY_________________________________________________________________
            if(isStrict){
                // с узорами
                if((phone_number[5] == 6 || phone_number[5] == 7) && isLower == 2
                        || phone_number[5] == 6 && galstuk == 1 && phone_number[5] != 4 && phone_number[5] != 2)
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "1X" + phone_number[7] + "9XX.png"; //одежда
                else
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "9XX.png"; //одежда

//_______________________GLASSES_OR_LAY_____________________________________________________________
                if(isGlasses){
                    filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";              //очки
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0);
                    addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]]);
                    addLayoutToCanvas(filename[9], canvas, p, 0);
                } else {
                    if((phone_number[9] == 6 || phone_number[9] == 7) && overlap3rd == 1
                            || phone_number[9] == 6 && galstuk == 1 && phone_number[5] != 4 && phone_number[5] != 2)
                        filename[9] = phone_number[3] + "" + phone_number[9] + "1" + phone_number[8];  //одежда
                    else
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];  //одежда
                    List<String> list = new ArrayList<String>();
                    list.add(filename[5]);
                    list.add(filename[9]);
                    Collections.sort(list, new MyIntComparable());
                    for(String integer : list){
                        addLayoutToCanvas(integer + "XXXX.png", canvas, p, 0);
                        if(filename[7].contains(String.valueOf(Integer.parseInt(integer) / 10))){
                            addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]]);
                        }
                    }
                }

//_______________________SECOND_LAY_________________________________________________________________
            } else {
                if ((phone_number[7] == 6 || phone_number[7] == 7) && isLower == 2
                        || phone_number[7] == 6 && galstuk == 1 && phone_number[5] != 4 && phone_number[5] != 2)
                    filename[7] = phone_number[3] + "" + phone_number[7] + "1" + phone_number[6]; //застегнута
                else
                    filename[7] = phone_number[3] + "" + phone_number[7] + "0" + phone_number[6]; //растегнута

//_______________________GLASSES_OR_LAY_____________________________________________________________
                if(isGlasses){
                    filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";              //очки
                    List<String> list = new ArrayList<String>();
                    list.add(filename[5]);
                    list.add(filename[7]);
                    Collections.sort(list, new MyIntComparable());
                    for(String integer : list){
                        addLayoutToCanvas(integer + "XXXX.png", canvas, p, 0);
                    }
                    addLayoutToCanvas(filename[9], canvas, p, 0);
                } else {
                    if((phone_number[9] == 6 || phone_number[9] == 7) && overlap3rd == 1
                            || phone_number[9] == 6 && galstuk == 1 && phone_number[5] != 4 && phone_number[5] != 2)
                        filename[9] = phone_number[3] + "" + phone_number[9] + "1" + phone_number[8];  //одежда
                    else
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];  //одежда
                    List<String> list = new ArrayList<String>();
                    list.add(filename[5]);
                    list.add(filename[7]);
                    list.add(filename[9]);
                    Collections.sort(list, new MyIntComparable());
                    for(String integer : list){
                        addLayoutToCanvas(integer + "XXXX.png", canvas, p, 0);
                    }
                }
            }
            System.out.println(filename[3] + "\n" + filename[5] + "\n" + filename[7] + "\n" + filename[9] + "\n");
            publishProgress();
            image = (ImageView)findViewById(R.id.imgCreateLogo);
            return true;
        }

        public class MyIntComparable implements Comparator<String>{
            @Override
            public int compare(String o1, String o2) {
                if(Integer.parseInt(o1)/10 == phone_number[3] * 100 || Integer.parseInt(o2)/10 == phone_number[3] * 100)
                    return 100;
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        }
    }
}


