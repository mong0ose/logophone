package com.logophone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by mongOose on 22.12.13.
 */
public class Visualizer extends Activity{
    private Bitmap bMap;
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
        setContentView(R.layout.logo_visualizer);
        new ImageBuilder().execute(7);

    }

    private class ImageBuilder extends AsyncTask<Integer, Integer, Boolean>{
        private Bitmap bmapOverlay;
        private ImageView image;
        private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
        private Integer phone_number[] = new Integer[10];

        @Override
        protected Boolean doInBackground(Integer... integers) {
            et1 = (EditText)findViewById(R.id.visEditText1);
            et2 = (EditText)findViewById(R.id.visEditText2);
            et3 = (EditText)findViewById(R.id.visEditText3);
            et4 = (EditText)findViewById(R.id.visEditText4);
            et5 = (EditText)findViewById(R.id.visEditText5);
            et6 = (EditText)findViewById(R.id.visEditText6);
            et7 = (EditText)findViewById(R.id.visEditText7);
            et8 = (EditText)findViewById(R.id.visEditText8);
            et9 = (EditText)findViewById(R.id.visEditText9);
            et10 = (EditText)findViewById(R.id.visEditText10);

            Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point p = new Point();
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
                p.x = disp.getWidth();
                p.y = disp.getHeight();
            } else
                disp.getSize(p);
            bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmapOverlay);
            Random rand = new Random();
            for(int i = 0; i < 10; i++){
                phone_number[i] = rand.nextInt(9-0) + 0;
            }
            Creator check = new Creator();
            Boolean isGlasses = check.checkForGlasses(phone_number);
            String[] filename = new String[10];
            int type = 0;
            switch(integers[0]){
                case 1:
                    type = rand.nextInt(9-0) + 0;
                    if(type >= 5){
                        filename[3] = phone_number[3] + "XXXXXXX.png";
                        addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                        //          char
                    } else {
                        canvas.drawColor(colors_array[phone_number[0]]);
                        //          background
                    }
                    break;
                case 2:
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[1], canvas, p, 0, 0);

                    break;
                case  3:
                    type = rand.nextInt(3-0) + 0;
                    switch (type){
                        case 0:
                            filename[3] = phone_number[3] + "XXXXXXX.png";
                            addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                            filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                            addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                            //          char
                            break;
                        case 1:
                            filename[0] = "flag/40.png";
                            addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                            break;
                        case 2:
                            filename[0] = "flag/10.png";
                            addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                            filename[1] = "flag/20.png";
                            addLayoutToCanvas(filename[1], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                            filename[2] = "flag/30.png";
                            addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[2]], Color.WHITE);
                            break;
                        case 3:
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                            filename[1] = "figure/" + phone_number[2] + "00.png";
                            addLayoutToCanvas(filename[1], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                    //          char
                    break;
                case 5:
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "9XX.png";
                    addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
                    //          char
                    break;
                case 6:
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "9XX.png";
                    addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
                    //          char
                    break;
                case 7:
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "9XX.png";
                    if(isGlasses){
                        filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                        addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
                        addLayoutToCanvas(filename[9], canvas, p, 0, 0);
                    }else{
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];
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
//                        addLayoutToCanvas(filename[9] + "XXXX.png", canvas, p, 0, 0);
                    }
                    //          char
                    break;
                case 8:
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "9XX.png";
                    if(isGlasses){
                        filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                        addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
                        addLayoutToCanvas(filename[9], canvas, p, 0, 0);
                    }else{
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];
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
//                        addLayoutToCanvas(filename[9] + "XXXX.png", canvas, p, 0, 0);
                    }
                    //          char
                    break;
                case 9:

                    break;
                case 10:

                    break;
                default:
                    break;
            }
            image = (ImageView)findViewById(R.id.visImage);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                if(phone_number.length != 0){
                    et1.setText((phone_number[0] != null) ? String.valueOf(phone_number[0]) : "");
                    et2.setText((phone_number[1] != null) ? String.valueOf(phone_number[1]) : "");
                    et3.setText((phone_number[2] != null) ? String.valueOf(phone_number[2]) : "");
                    et4.setText((phone_number[3] != null) ? String.valueOf(phone_number[3]) : "");
                    et5.setText((phone_number[4] != null) ? String.valueOf(phone_number[4]) : "");
                    et6.setText((phone_number[5] != null) ? String.valueOf(phone_number[5]) : "");
                    et7.setText((phone_number[6] != null) ? String.valueOf(phone_number[6]) : "");
                    et8.setText((phone_number[7] != null) ? String.valueOf(phone_number[7]) : "");
                    et9.setText((phone_number[8] != null) ? String.valueOf(phone_number[8]) : "");
                    et10.setText((phone_number[9] != null) ? String.valueOf(phone_number[9]) : "");

                    image.setImageBitmap(bmapOverlay);
                }
            }
        }

        public class MyIntComparable implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                if(Integer.parseInt(o1)/10 == phone_number[3] * 100)
                    return 100;
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        }
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

    private void addLayoutToCanvas(String filename, Canvas canvas, Point p, int color, int bColor){
        try {
            InputStream is = getAssets().open("raw/" + filename);
            bMap = BitmapFactory.decodeStream(is);
            is.close();
            canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, bColor, color), new Matrix(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
