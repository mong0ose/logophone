package com.logophone;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by mongOose on 22.01.14.
 */
public class TrainingPart extends Activity {
    private Bitmap bMap;
    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
    private EditText[] etArray = new EditText[]{};
    private ViewFlipper viewFlipper;
    private RadioGroup group;
    private RadioButton rButton;
    private Context mContext = this;
    private ImageView image;
    private int TypeSize;
    private float lastX;
    private Integer charge_number[] = new Integer[10];
    private ProgressDialog mProgressDialog;
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
        setContentView(R.layout.logo_training);

//        etArray[0] = (EditText)findViewById(R.id.trainEditText1);
//        etArray[1] = (EditText)findViewById(R.id.trainEditText2);
//        etArray[2] = (EditText)findViewById(R.id.trainEditText3);
//        etArray[3] = (EditText)findViewById(R.id.trainEditText4);
//        etArray[4] = (EditText)findViewById(R.id.trainEditText5);
//        etArray[5] = (EditText)findViewById(R.id.trainEditText6);
//        etArray[6] = (EditText)findViewById(R.id.trainEditText7);
//        etArray[7] = (EditText)findViewById(R.id.trainEditText8);
//        etArray[8] = (EditText)findViewById(R.id.trainEditText9);
//        etArray[9] = (EditText)findViewById(R.id.trainEditText10);
//
//        for (int cnt=0; cnt<etArray.length; cnt++){
//            etArray[cnt].addTextChangedListener(new TextWatcher() {
//                private int c;
//
//                public void TextWatcher(int cnt){
//                    c = cnt;
//                }
//
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//                    if(etArray[c].getText().toString().length() > 0 && etArray[c+1].isFocusable()){
//                        etArray[c+1].requestFocus();
//                    } else if(c == 0 && etArray[c].getText().toString().length() > 0 && !etArray[c+1].isFocusable()){
//                        etArray[3].requestFocus();
//                    } else if(etArray[c].getText().toString().length() < 1 && etArray[c-1].isFocusable() && c != 0){
//                        etArray[c-1].requestFocus();
//                    } else if(c == 3 && etArray[c].getText().toString().length() < 1 && !etArray[c-1].isFocusable() ){
//                        etArray[0].requestFocus();
//                    }
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//
//                }
//            });
//        }
        
        et1 = (EditText)findViewById(R.id.trainEditText1);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et1.getText().toString().length() > 0 && et2.isFocusable())
                    et2.requestFocus();
                else
                    et4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2 = (EditText)findViewById(R.id.trainEditText2);
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et2.getText().toString().length() > 0)
                    et3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et3 = (EditText)findViewById(R.id.trainEditText3);
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et3.getText().toString().length() > 0)
                    et4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et4 = (EditText)findViewById(R.id.trainEditText4);
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et4.getText().toString().length() > 0)
                    et5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et5 = (EditText)findViewById(R.id.trainEditText5);
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et5.getText().toString().length() > 0)
                    et6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et6 = (EditText)findViewById(R.id.trainEditText6);
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et6.getText().toString().length() > 0)
                    et7.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et7 = (EditText)findViewById(R.id.trainEditText7);
        et7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et7.getText().toString().length() > 0)
                    et8.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et8 = (EditText)findViewById(R.id.trainEditText8);
        et8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et8.getText().toString().length() > 0)
                    et9.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et9 = (EditText)findViewById(R.id.trainEditText9);
        et9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et9.getText().toString().length() > 0)
                    et10.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et10 = (EditText)findViewById(R.id.trainEditText10);

        TypeSize = 1;
        viewFlipper = (ViewFlipper) findViewById(R.id.trainViewFlipper);

        group = (RadioGroup) findViewById(R.id.trainRadioSize);
        rButton = (RadioButton) findViewById(R.id.radioButtonTrain);
        rButton.setChecked(true);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rButton = (RadioButton) findViewById(i);
                TypeSize = Integer.parseInt((String) rButton.getText());
            }
        });
        image = (ImageView)findViewById(R.id.trainImage);
        Button bCheck = (Button)findViewById(R.id.btnCheckTrain);
        bCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tel[] = new Integer[10];
                        tel[0] = et1.getText().length() != 0 ? Integer.parseInt(String.valueOf(et1.getText().toString())) : null;
                        tel[1] = et2.getText().length() != 0 ? Integer.parseInt(String.valueOf(et2.getText().toString())) : null;
                        tel[2] = et3.getText().length() != 0 ? Integer.parseInt(String.valueOf(et3.getText().toString())) : null;
                        tel[3] = et4.getText().length() != 0 ? Integer.parseInt(String.valueOf(et4.getText().toString())) : null;
                        tel[4] = et5.getText().length() != 0 ? Integer.parseInt(String.valueOf(et5.getText().toString())) : null;
                        tel[5] = et6.getText().length() != 0 ? Integer.parseInt(String.valueOf(et6.getText().toString())) : null;
                        tel[6] = et7.getText().length() != 0 ? Integer.parseInt(String.valueOf(et7.getText().toString())) : null;
                        tel[7] = et8.getText().length() != 0 ? Integer.parseInt(String.valueOf(et8.getText().toString())) : null;
                        tel[8] = et9.getText().length() != 0 ? Integer.parseInt(String.valueOf(et9.getText().toString())) : null;
                        tel[9] = et10.getText().length() != 0 ? Integer.parseInt(String.valueOf(et10.getText().toString())) : null;
                if(Arrays.equals(tel, charge_number)){
                    Toast.makeText(mContext, "CORRECT!", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(mContext, "WRONG NUMBER!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_to_right));
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_left));
//                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_to_right));
                    // Show the next Screen
                    new ImageBuilder().execute();
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_to_left));
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
//                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_to_left));
                    // Show The Previous Screen
                    new ImageBuilder().execute();
                }
                break;
            }
        }
        return false;
    }

    private class ImageBuilder extends AsyncTask<Integer, Integer, Boolean> {
        private Bitmap bmapOverlay;
        private Integer phone_number[] = new Integer[10];


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            charge_number = new Integer[10];
            et1.setText("");
            et1.setFocusable(false);
            et2.setText("");
            et2.setFocusable(false);
            et3.setText("");
            et3.setFocusable(false);
            et4.setText("");
            et4.setFocusable(false);
            et5.setText("");
            et5.setFocusable(false);
            et6.setText("");
            et6.setFocusable(false);
            et7.setText("");
            et7.setFocusable(false);
            et8.setText("");
            et8.setFocusable(false);
            et9.setText("");
            et9.setFocusable(false);
            et10.setText("");
            et10.setFocusable(false);
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Создание логотипа...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
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
            switch(TypeSize){
                case 1:
                    type = rand.nextInt(9-0) + 0;
                    if(type >= 5){
                        charge_number[3] = phone_number[3];
                        filename[3] = phone_number[3] + "XXXXXXX.png";
                        addLayoutToCanvas(filename[3], canvas, p, 0, 0);
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
                    addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[1], canvas, p, 0, 0);

                    break;
                case  3:
                    type = rand.nextInt(3-0) + 0;
                    switch (type){
                        case 0:
                            charge_number[3] = phone_number[3];
                            charge_number[4] = phone_number[4];
                            charge_number[5] = phone_number[5];
                            filename[3] = phone_number[3] + "XXXXXXX.png";
                            addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                            filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                            addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                            //          char
                            break;
                        case 1:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[0] = "flag/40.png";
                            addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                            break;
                        case 2:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[0] = "flag/10.png";
                            addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                            filename[1] = "flag/20.png";
                            addLayoutToCanvas(filename[1], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                            filename[2] = "flag/30.png";
                            addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[2]], Color.WHITE);
                            break;
                        case 3:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
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
                    charge_number[0] = phone_number[0];
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], canvas, p, colors_array[phone_number[0]], Color.WHITE);
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                    //          char
                    break;
                case 5:
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    filename[3] = phone_number[3] + "XXXXXXX.png";
                    addLayoutToCanvas(filename[3], canvas, p, 0, 0);
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, p, 0, 0);
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "9XX.png";
                    addLayoutToCanvas(filename[7], canvas, p, colors_array[phone_number[6]], colors_array[9]);
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
                    charge_number[3] = phone_number[3];
                    charge_number[4] = phone_number[4];
                    charge_number[5] = phone_number[5];
                    charge_number[6] = phone_number[6];
                    charge_number[7] = phone_number[7];
                    charge_number[8] = phone_number[8];
                    charge_number[9] = phone_number[9];
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
                    addLayoutToCanvas(filename[2], canvas, p, colors_array[phone_number[1]], Color.WHITE);
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[1], canvas, p, colors_array[phone_number[0]], Color.WHITE);
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
                    et1.setFocusableInTouchMode((charge_number[0] != null) ? true : false);
                    et2.setFocusableInTouchMode((charge_number[1] != null) ? true : false);
                    et3.setFocusableInTouchMode((charge_number[2] != null) ? true : false);
                    et4.setFocusableInTouchMode((charge_number[3] != null) ? true : false);
                    et5.setFocusableInTouchMode((charge_number[4] != null) ? true : false);
                    et6.setFocusableInTouchMode((charge_number[5] != null) ? true : false);
                    et7.setFocusableInTouchMode((charge_number[6] != null) ? true : false);
                    et8.setFocusableInTouchMode((charge_number[7] != null) ? true : false);
                    et9.setFocusableInTouchMode((charge_number[8] != null) ? true : false);
                    et10.setFocusableInTouchMode((charge_number[9] != null) ? true : false);

                    image.setImageBitmap(bmapOverlay);
                    viewFlipper.showNext();
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
