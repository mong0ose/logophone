package com.logophone;

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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by mongOose on 13.11.13.
 */
public class ContactsTest extends Activity {
    private Bitmap bMap;
    private Integer phone_number[] = new Integer[10];
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
        ImageView image;
        EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;

        Bitmap bmapOverlay;
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            phone_number[i] = rand.nextInt(9-0) + 0;
        }


        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point p = new Point();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);

        bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmapOverlay);
        Button bGet = (Button)findViewById(R.id.btnConstContacts);
        bGet.setVisibility(View.GONE);
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

        et1.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
        et3.setVisibility(View.GONE);
        et4.setVisibility(View.GONE);
        et5.setVisibility(View.GONE);
        et6.setVisibility(View.GONE);
        et7.setVisibility(View.GONE);
        et8.setVisibility(View.GONE);
        et9.setVisibility(View.GONE);
        et10.setVisibility(View.GONE);

        String[] filename = new String[10];
        Creator check = new Creator();
        int overlap = check.chechForOverlap(phone_number);
        int overlap3rd = check.checkForOverlap_3rd(phone_number);
        int galstuk = check.checkGalstuk(phone_number);
        int isLower = check.checkLower(phone_number);
        Boolean isGlasses = check.checkForGlasses(phone_number);
        Boolean isStrict = check.checkForStrict(phone_number);

        Random rands = new Random();
        int type = rands.nextInt(2-0) + 0;
        switch (type){
            case 0:
                filename[0] = "flag/40.png";
                addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                filename[2] = "figure/" + phone_number[2] + "01.png";
                addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                break;
            case 1:
                filename[0] = "flag/10.png";
                addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                filename[1] = "flag/20.png";
                addLayoutToCanvas(filename[1], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                filename[2] = "flag/30.png";
                addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[2]], Color.WHITE);
                break;
            case 2:
                filename[2] = "figure/" + phone_number[2] + "01.png";
                addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                filename[1] = "figure/" + phone_number[2] + "00.png";
                addLayoutToCanvas(filename[1], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                break;
            default:
                break;
        }

//_______________________CHARACTER__________________________________________________________________
        filename[3] = phone_number[3] + "XXXXXXX.png";
        addLayoutToCanvas(filename[3], canvas, p, 0, 0);

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
                addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
                addLayoutToCanvas(filename[9], canvas, p, 0, 0);
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
                    addLayoutToCanvas(integer + "XXXX.png", canvas, p, 0, 0);
                    if(filename[7].contains(String.valueOf(Integer.parseInt(integer) / 10))){
                        addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
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
                    addLayoutToCanvas(integer + "XXXX.png", canvas, p, 0, 0);
                }
                addLayoutToCanvas(filename[9], canvas, p, 0, 0);
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
                    addLayoutToCanvas(integer + "XXXX.png", canvas, p, 0, 0);
                }
            }
        }
        System.out.println(filename[3] + "\n" + filename[5] + "\n" + filename[7] + "\n" + filename[9] + "\n");
        image = (ImageView)findViewById(R.id.imgCreateLogo);
        image.setImageBitmap(bmapOverlay);
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

    private void addLayoutToCanvas(String filename, Canvas canvas, Point p, int color, int changeColor){
        try {
            InputStream is = getAssets().open("raw/" + filename);
            bMap = BitmapFactory.decodeStream(is);
            is.close();
            canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, changeColor, color), new Matrix(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
