package com.logophone;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
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
import android.widget.Toast;

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
    private static final int PICK_CONTACT = 3245;
    private ProgressDialog mProgressDialog;
    private Context mContext = this;
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
                Intent iPick = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(iPick, PICK_CONTACT);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, ContactsContract.CommonDataKinds.Phone.NUMBER, null, null);
                    if (c.moveToFirst()) {
                        String phone = (String) c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        new CreateImageFromContact().execute(convert(phone.replaceAll("\\D+", "")));
                    }
                }
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
        Integer number[] = new Integer[replace.length];
        for (int i = 0; i < replace.length; i++)
            number[i] = Integer.parseInt(String.valueOf(replace[i]));

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Создание логотипа...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
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
            } else {
                Toast.makeText(mContext, "Bad phone number!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Boolean doInBackground(Integer... ints) {
            if(ints.length != 10) return false;
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
                if(Integer.parseInt(o1)/10 == phone_number[3] * 100)
                    return 100;
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        }
    }
}


