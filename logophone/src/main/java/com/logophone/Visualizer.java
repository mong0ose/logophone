package com.logophone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

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
    private ViewFlipper viewFlipper;
    private RadioGroup group;
    private RadioButton rButton;
    private Context mContext = this;
    private ImageView image;
    private int TypeSize, showtype;
    private float lastX;
    private ProgressDialog mProgressDialog;
    private Point p = new Point();
    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
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
        setContentView(R.layout.logo_visualizer);
        TypeSize = 1;
        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);
        p.y = p.x*1528/1080;

        et1 = (EditText)findViewById(R.id.visEditText1);
        et1.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et2 = (EditText)findViewById(R.id.visEditText2);
        et2.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et3 = (EditText)findViewById(R.id.visEditText3);
        et3.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et4 = (EditText)findViewById(R.id.visEditText4);
        et4.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et5 = (EditText)findViewById(R.id.visEditText5);
        et5.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et6 = (EditText)findViewById(R.id.visEditText6);
        et6.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et7 = (EditText)findViewById(R.id.visEditText7);
        et7.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et8 = (EditText)findViewById(R.id.visEditText8);
        et8.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et9 = (EditText)findViewById(R.id.visEditText9);
        et9.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        et10 = (EditText)findViewById(R.id.visEditText10);
        et10.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
        
        rButton = (RadioButton) findViewById(R.id.radioButton);
        rButton.setChecked(true);
        viewFlipper = (ViewFlipper) findViewById(R.id.visViewFlipper);
        group = (RadioGroup) findViewById(R.id.radioSize);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rButton = (RadioButton) findViewById(i);
                TypeSize = Integer.parseInt((String) rButton.getText());
            }
        });
        image = (ImageView)findViewById(R.id.visImage);

        new ImageBuilder().execute(1);
    }

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {
                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen
                    image.setImageBitmap(null);
                    new ImageBuilder().execute(5);
//                    viewFlipper.showNext();
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen
                    image.setImageBitmap(null);
                    new ImageBuilder().execute(5);
//                    viewFlipper.showPrevious();
                }
                break;
            }
        }
        return false;
    }

    private class ImageBuilder extends AsyncTask<Integer, Integer, Boolean>{
        private Bitmap bmapOverlay;
        private Integer phone_number[] = new Integer[10];
        private Integer charge_number[] = new Integer[10];

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
            et6.setText("");
            et7.setText("");
            et8.setText("");
            et9.setText("");
            et10.setText("");
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Создание логотипа...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmapOverlay);
            Random rand = new Random();
            for(int i = 0; i < 10; i++){
                phone_number[i] = rand.nextInt(9-0) + 0;
            }
            Creator check = new Creator(phone_number);

            int[] aZipperType = check.GetZipperType();
            showtype = 0;
            if(check.newCheckPleasure())
                showtype = 2;
            else if(check.newCheckStrict())
                showtype = 1;

            Boolean isGlasses = check.checkForGlasses(phone_number);
            String[] filename = new String[10];
            int type = 0;
            switch(TypeSize){
                case 1:
                    type = rand.nextInt(9-0) + 0;
                    if(type >= 5){
                        charge_number[3] = phone_number[3];
                        filename[3] = phone_number[3] + "XXXXXXX.png";
                        addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                        //          char
                    } else {
                        charge_number[0] = phone_number[0];
                        canvas.drawColor(colors_array[phone_number[0]]);
                        //          background
                    }
                    break;
                case 2:
                    charge_number[1] = phone_number[1];
                    charge_number[2] = phone_number[2];
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[1], canvas, 0, 0, false);

                    break;
                case  3:
                    type = rand.nextInt(3-0) + 0;
                    switch (type){
                        case 0:
                            charge_number[3] = phone_number[3];
                            charge_number[4] = phone_number[4];
                            charge_number[5] = phone_number[5];
                            filename[3] = phone_number[3] + "XXXXXXX.png";
                            addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                            filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                            addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                            //          char
                            break;
                        case 1:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[0] = "flag/40.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            break;
                        case 2:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[0] = "flag/10.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            filename[1] = "flag/20.png";
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            filename[2] = "flag/30.png";
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[2]], Color.WHITE, true);
                            break;
                        case 3:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            filename[1] = "figure/" + phone_number[2] + "00.png";
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    charge_number[0] = phone_number[0];
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                    //          char
                    break;
                case 5:
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                    //          char
                    break;
                case 6:
                    charge_number[0] = phone_number[0];
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                    //          char
                    break;
                case 7:
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    charge_number[8] = phone_number[8];
                    charge_number[9] = phone_number[9];
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    if(isGlasses){
                        filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                        addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                        addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                    }else{
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];
                        List<String> list = new ArrayList<String>();
                        list.add(filename[5]);
                        list.add(filename[9]);
                        Collections.sort(list, new MyIntComparable());
                        for(String integer : list){
                            addLayoutToCanvas(integer + "XXXX.png", canvas, 0, 0, false);
                            if(filename[7].contains(String.valueOf(Integer.parseInt(integer) / 10))){
                                addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                            }
                        }
                    }
                    //          char
                    break;
                case 8:
                    charge_number[0] = phone_number[0];
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    charge_number[8] = phone_number[8];
                    charge_number[9] = phone_number[9];
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    if(isGlasses){
                        filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                        addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                        addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                    }else{
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];
                        List<String> list = new ArrayList<String>();
                        list.add(filename[5]);
                        list.add(filename[9]);
                        Collections.sort(list, new MyIntComparable());
                        for(String integer : list){
                            addLayoutToCanvas(integer + "XXXX.png", canvas, 0, 0, false);
                            if(filename[7].contains(String.valueOf(Integer.parseInt(integer) / 10))){
                                addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                            }
                        }
                    }
                    //          char
                    break;
                case 9:
                    charge_number[1] = phone_number[1];
                    charge_number[2] = phone_number[2];
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    charge_number[8] = phone_number[8];
                    charge_number[9] = phone_number[9];
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    if(isGlasses){
                        filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                        addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                        addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                    }else{
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];
                        List<String> list = new ArrayList<String>();
                        list.add(filename[5]);
                        list.add(filename[9]);
                        Collections.sort(list, new MyIntComparable());
                        for(String integer : list){
                            addLayoutToCanvas(integer + "XXXX.png", canvas, 0, 0, false);
                            if(filename[7].contains(String.valueOf(Integer.parseInt(integer) / 10))){
                                addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                            }
                        }
                    }
                    //          char
                    break;
                case 10:
                    charge_number[0] = phone_number[0];
                    charge_number[1] = phone_number[1];
                    charge_number[2] = phone_number[2];
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    charge_number[8] = phone_number[8];
                    charge_number[9] = phone_number[9];
                    type = rand.nextInt(2-0) + 0;
                    switch (type){
                        case 0:
                            filename[0] = "flag/40.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            break;
                        case 1:
                            filename[0] = "flag/10.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            filename[1] = "flag/20.png";
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            filename[2] = "flag/30.png";
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[2]], Color.WHITE, true);
                            break;
                        case 2:
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            filename[1] = "figure/" + phone_number[2] + "00.png";
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            break;
                        default:
                            break;
                    }
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    if(isGlasses){
                        filename[9] = "glasses/" + phone_number[3] + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                        addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                        addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                    }else{
                        filename[9] = phone_number[3] + "" + phone_number[9] + "0" + phone_number[8];
                        List<String> list = new ArrayList<String>();
                        list.add(filename[5]);
                        list.add(filename[9]);
                        Collections.sort(list, new MyIntComparable());
                        for(String integer : list){
                            addLayoutToCanvas(integer + "XXXX.png", canvas, 0, 0, false);
                            if(filename[7].contains(String.valueOf(Integer.parseInt(integer) / 10))){
                                addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            mProgressDialog.dismiss();
            if(aBoolean){
                if(phone_number.length != 0){
                    et1.setText((charge_number[0] != null) ? String.valueOf(phone_number[0]) : "");
                    et2.setText((charge_number[1] != null) ? String.valueOf(phone_number[1]) : "");
                    et3.setText((charge_number[2] != null) ? String.valueOf(phone_number[2]) : "");
                    et4.setText((charge_number[3] != null) ? String.valueOf(phone_number[3]) : "");
                    et5.setText((charge_number[4] != null) ? String.valueOf(phone_number[4]) : "");
                    et6.setText((charge_number[5] != null) ? String.valueOf(phone_number[5]) : "");
                    et7.setText((charge_number[6] != null) ? String.valueOf(phone_number[6]) : "");
                    et8.setText((charge_number[7] != null) ? String.valueOf(phone_number[7]) : "");
                    et9.setText((charge_number[8] != null) ? String.valueOf(phone_number[8]) : "");
                    et10.setText((charge_number[9] != null) ? String.valueOf(phone_number[9]) : "");


                    image.setScaleX((float) 0.95);
                    image.setScaleY((float) 0.95);
                    image.setImageBitmap(bmapOverlay);
                    viewFlipper.showPrevious();
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
        Bitmap rbMap = Bitmap.createScaledBitmap(bbMap, w, h, true);
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

    private void addLayoutToCanvas(String filename, Canvas canvas, int color, int changeColor, boolean isFill){
        try {
            InputStream is = getAssets().open("raw/" + filename);
            Bitmap bMap = BitmapFactory.decodeStream(is);
            is.close();
            if(isFill){
                Paint paint = new Paint(color);
                ColorFilter filter = new LightingColorFilter(color, 1);
                paint.setColorFilter(filter);
                canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), paint);
            } else
                canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, changeColor, color), new Matrix(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
