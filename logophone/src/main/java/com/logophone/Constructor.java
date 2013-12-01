package com.logophone;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mongOose on 11.11.13.
 */
public class Constructor extends Activity {
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
        int phone_number[] = {};

        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point p = new Point();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);
        System.out.println("Screen resolution: " + p.x + "x" + p.y);
        Bitmap bMap, bmapOverlay;
        bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmapOverlay);

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

        if(phone_number.length > 0){
            et1.setText(phone_number[1]);
            et2.setText(phone_number[2]);
            et3.setText(phone_number[3]);
            et4.setText(phone_number[4]);
            et5.setText(phone_number[5]);
            et6.setText(phone_number[6]);
            et7.setText(phone_number[7]);
            et8.setText(phone_number[8]);
            et9.setText(phone_number[9]);
            et10.setText(phone_number[10]);
        }

        String[] filename = null;
//        int[] number_array = convert(phone_number);
        Creator check = new Creator();
        boolean strict_state = check.checkForStrict(phone_number);

        filename[3] = phone_number[3] + "00000.png";
        bMap = BitmapFactory.decodeFile(filename[3]);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        filename[5] = phone_number[3] + phone_number[5] + phone_number[4] + "000.png";
        bMap = BitmapFactory.decodeFile(filename[5]);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        if(strict_state){
            // с узорами
            filename[7] = phone_number[3] + phone_number[5] + "0" + phone_number[6] + "00.png";
            bMap = BitmapFactory.decodeFile(filename[7]);
            canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, Color.BLACK, colors_array[phone_number[7]]), new Matrix(), null);
//------
            if(check.checkForGlasses(phone_number)){
                filename[9] = phone_number[3] + "000" + phone_number[9] + "0.png";
                bMap = BitmapFactory.decodeFile(filename[9]);
                canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, Color.BLACK, colors_array[phone_number[7]]), new Matrix(), null);
            } else {
                filename[9] = phone_number[3] + phone_number[9] + phone_number[8] + "000.png";
                bMap = BitmapFactory.decodeFile(filename[9]);
                canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, Color.BLACK, colors_array[phone_number[7]]), new Matrix(), null);
            }
        } else {

        }


//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._21);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._22);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._23);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y,Color.WHITE, Color.RED), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._24);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._25);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._26);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._27);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._28);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);

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


    private String[] get_filename_of_image(int element, String[] number){
        String[] filename = null;
        int[] number_array = convert(number);
        Creator check = new Creator();
        boolean strict_state = check.checkForStrict(number_array);
        filename[3] = number[3] + "000000.png";

        filename[5] = number[3] + number[5] + number[4] + "0000.png";
        if(strict_state){

        } else {
            // с узорами
            filename[7] = number[3] + number[5] + number[4] + number[6] + "000.png";
        }
        return filename;
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
}
