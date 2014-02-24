package com.logophone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by mongOose on 22.01.14.
 */
public class TrainingPart extends Activity {
    private Integer charge_number[] = new Integer[10];
    private ViewFlipper viewFlipper;
    private RadioGroup group;
    private RadioButton rButton;
    private Context mContext = this;
    private ImageView image, image2;
    private int TypeSize, showtype;
    private Point p = new Point();
    private EditText[] editTextsTraining;
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
        Intent mIntent = getIntent();
        TypeSize = mIntent.getIntExtra("intValType", 1);
        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);
        p.y = p.x*1528/1080;
        p.x *= 0.95;
        p.y *= 0.95;

        editTextsTraining = new EditText[]{
                (EditText)findViewById(R.id.trainEditText1),
                (EditText)findViewById(R.id.trainEditText2),
                (EditText)findViewById(R.id.trainEditText3),
                (EditText)findViewById(R.id.trainEditText4),
                (EditText)findViewById(R.id.trainEditText5),
                (EditText)findViewById(R.id.trainEditText6),
                (EditText)findViewById(R.id.trainEditText7),
                (EditText)findViewById(R.id.trainEditText8),
                (EditText)findViewById(R.id.trainEditText9),
                (EditText)findViewById(R.id.trainEditText10)};

        editTextsTraining[0].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (editTextsTraining[0].getText().toString().length() > 0 && editTextsTraining[1].isFocusable())
                    editTextsTraining[1].requestFocus();
                else
                    editTextsTraining[3].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[1].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[1].getText().toString().length() > 0)
                    editTextsTraining[2].requestFocus();
                else if(editTextsTraining[1].getText().toString().length() < 1)
                    editTextsTraining[0].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[2].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[2].getText().toString().length() > 0)
                    editTextsTraining[3].requestFocus();
                else if(editTextsTraining[2].getText().toString().length() < 1)
                    editTextsTraining[1].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[3].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[3].getText().toString().length() > 0)
                    editTextsTraining[4].requestFocus();
                else if(editTextsTraining[3].getText().toString().length() < 1 && editTextsTraining[2].isFocusable())
                    editTextsTraining[2].requestFocus();
                else if(editTextsTraining[3].getText().toString().length() < 1)
                    editTextsTraining[0].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[4].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[4].getText().toString().length() > 0)
                    editTextsTraining[5].requestFocus();
                else if(editTextsTraining[4].getText().toString().length() < 1)
                    editTextsTraining[3].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[5].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[5].getText().toString().length() > 0)
                    editTextsTraining[6].requestFocus();
                else if(editTextsTraining[5].getText().toString().length() < 1)
                    editTextsTraining[4].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[6].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[6].getText().toString().length() > 0)
                    editTextsTraining[7].requestFocus();
                else if(editTextsTraining[6].getText().toString().length() < 1)
                    editTextsTraining[5].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[7].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[7].getText().toString().length() > 0)
                    editTextsTraining[8].requestFocus();
                else if(editTextsTraining[7].getText().toString().length() < 1)
                    editTextsTraining[6].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[8].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[8].getText().toString().length() > 0)
                    editTextsTraining[9].requestFocus();
                else if(editTextsTraining[8].getText().toString().length() < 1)
                    editTextsTraining[7].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextsTraining[9].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(editTextsTraining[9].getText().toString().length() < 1)
                    editTextsTraining[8].requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ImageButton ibNext = (ImageButton)findViewById(R.id.imgBtnCheckNumber);
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer tel[] = new Integer[10];
                for (int i = 0; i < editTextsTraining.length; i++)
                    tel[i] = editTextsTraining[i].getText().length() != 0 ? Integer.parseInt(String.valueOf(editTextsTraining[i].getText().toString())) : null;
                if(Arrays.equals(tel, charge_number)){
                    Toast.makeText(mContext, "CORRECT!", Toast.LENGTH_SHORT).show();
                } else{
                    for(int i = 0; i < editTextsTraining.length; i++)
                        editTextsTraining[i].setText("");
                    Toast.makeText(mContext, "WRONG NUMBER!", Toast.LENGTH_SHORT).show();
                }
                new ImageBuilder().execute();
            }
        });

        rButton = (RadioButton) findViewById(R.id.radioButtonTrain);
        rButton.setChecked(true);
        viewFlipper = (ViewFlipper) findViewById(R.id.trainViewFlipper);
        viewFlipper.setInAnimation(this, R.anim.in_from_right);
        viewFlipper.setOutAnimation(this, R.anim.out_to_left);
        group = (RadioGroup) findViewById(R.id.trainRadioSize);
        group.check(0);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rButton = (RadioButton) findViewById(i);
                TypeSize = Integer.parseInt((String) rButton.getText());
                new ImageBuilder().execute();
            }
        });
        image = (ImageView)findViewById(R.id.trainImage);
        image2 = (ImageView)findViewById(R.id.trainImage2);

        new ImageBuilder().execute();
    }

    private class ImageBuilder extends AsyncTask<Integer, Integer, Boolean>{
        private Bitmap bmapOverlay;
        private Integer phone_number[] = new Integer[10];
        private String[] filename = new String[10];

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            charge_number = new Integer[10];
            for(int i = 0; i < editTextsTraining.length; i++){
                editTextsTraining[i].setText("");
                editTextsTraining[i].setFocusableInTouchMode(false);
            }
        }

        private void setTrueNumeric(int[] aSorted, int[] aZipperType, Canvas canvas, int indexOfClothes, int indexOfUzor){
            switch (showtype){
                case 2:
                    System.out.println(indexOfClothes + "\n" + indexOfUzor);
                    for(int i = 0; i < aSorted.length; i++){
                        if(i == indexOfClothes){
                            filename[5] = (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i])) + "XXXX.png";
                            filename[7] = "uzors/" + (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1, 4) : String.valueOf(aSorted[i]).substring(0, 3))
                                    + "X" + (String.valueOf(aSorted[indexOfUzor]).length() > 4 ? String.valueOf(aSorted[indexOfUzor]).substring(2, 3) : String.valueOf(aSorted[indexOfUzor]).substring(1, 2))
                                    + "0XX.png";
                            System.out.println(filename[5]);
                            System.out.println(filename[7]);
                            addLayoutToCanvas(filename[5], canvas, 0, 0, false);
                            addLayoutToCanvas(filename[7], canvas, colors_array[aSorted[indexOfUzor] % 10], colors_array[9], true);
                        } else if(i == indexOfUzor && aSorted[i] == aSorted[indexOfUzor]){
                            //do nothing
                        } else if(TypeSize > 6) {
                            filename[9] = (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i])) + "XXXX.png";
                            System.out.println(filename[9]);
                            addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                        }
                    }

                    break;
                case 1:
                    filename[5] = (String.valueOf(aZipperType[0]).length() > 4 ? String.valueOf(aZipperType[0]).substring(1) : String.valueOf(aZipperType[0])) + "XXXX.png";
                    filename[7] = "uzors/" + (String.valueOf(aZipperType[0]).length() > 4 ? String.valueOf(aZipperType[0]).substring(1, 4) : String.valueOf(aZipperType[0]).substring(0, 3))
                            + "X" + (String.valueOf(aZipperType[1]).length() > 4 ? String.valueOf(aZipperType[1]).substring(2, 3) : String.valueOf(aZipperType[1]).substring(1, 2)) + "0XX.png";
                    System.out.println(filename[5]);
                    System.out.println(filename[7]);
                    addLayoutToCanvas(filename[5], canvas, 0, 0, false);
                    addLayoutToCanvas(filename[7], canvas, colors_array[aZipperType[1] % 10], colors_array[9], true);
                    if(TypeSize > 6) {
                        filename[9] = "glasses/" + (phone_number[3] == 10 ? 0 : phone_number[3]) + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        System.out.println(filename[9]);
                        addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                    }
                    break;
                default:
                    break;
            }
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

            int[] aSorted = check.SortClothes(aZipperType);
            int type = 0;
            int indexOfClothes = check.indexOfIntArray(aSorted, aZipperType[0]);
            int indexOfUzor = check.indexOfIntArray(aSorted, aZipperType[1]);

            filename[0] = "flag/40.png";
            phone_number[3] = (phone_number[3] == 10 ? 0 : phone_number[3]);
            filename[3] = phone_number[3] + "XXXXXXX.png";

            switch (TypeSize) {
                case 1:
                    type = rand.nextInt(9-0) + 0;
                    if(type >= 5){
                        charge_number[3] = phone_number[3];
                        addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                        //          char
                    } else {
                        charge_number[0] = phone_number[0];
                        addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                        //          background
                    }
                    break;
                case 2:
                    charge_number[1] = phone_number[1];
                    charge_number[2] = phone_number[2];
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[0], canvas, 0, 0, false);
                    addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                    break;
                case 3:
                    type = rand.nextInt(4-0) + 0;
                    switch (type){
                        case 0:
                            charge_number[3] = phone_number[3];
                            charge_number[4] = phone_number[4];
                            charge_number[5] = phone_number[5];
                            filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                            addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                            addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                            //          char
                            break;
                        case 1:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            break;
                        case 2:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[0] = "flag/10.png";
                            filename[1] = "flag/20.png";
                            filename[2] = "flag/30.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[2]], Color.WHITE, true);
                            break;
                        case 3:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            filename[1] = "figure/" + phone_number[2] + "00.png";
                            addLayoutToCanvas(filename[0], canvas, 0, 0, false);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
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
                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
                    break;
                case 5:
                    for(int i = 3; i < phone_number.length - 2; i++)
                        charge_number[i] = phone_number[i];

                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
//                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
//                    addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                    break;
                case 6:
                    charge_number[0] = phone_number[0];
                    for(int i = 3; i < phone_number.length - 2; i++)
                        charge_number[i] = phone_number[i];

                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
//                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, 0, false);
//                    addLayoutToCanvas(filename[7], canvas, colors_array[phone_number[6]], colors_array[9], true);
                    break;
                case 7:
                    for(int i = 3; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];

                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 8:
                    charge_number[0] = phone_number[0];
                    for(int i = 3; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];

                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 9:
                    for(int i = 1; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];

                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[0], canvas, 0, 0, false);
                    addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                    addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 10:
                    for(int i = 0; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];

                    type = rand.nextInt(3-0) + 0;
                    switch (type){
                        case 0:
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            break;
                        case 1:
                            filename[0] = "flag/10.png";
                            filename[1] = "flag/20.png";
                            filename[2] = "flag/30.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[2]], Color.WHITE, true);
                            break;
                        case 2:
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            filename[1] = "figure/" + phone_number[2] + "00.png";
                            addLayoutToCanvas(filename[0], canvas, 0, 0, false);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], Color.WHITE, true);
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[0]], Color.WHITE, true);
                            break;
                        default:
                            break;
                    }
                    addLayoutToCanvas(filename[3], canvas, 0, 0, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                default:
                    break;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                if(phone_number.length != 0){
                    for (int i = 0; i < editTextsTraining.length; i++){
                        editTextsTraining[i].setFocusableInTouchMode((charge_number[i] != null) ? true : false);
                        if(charge_number[i] == null)
                            editTextsTraining[i].getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                        else
                            editTextsTraining[i].getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    }
                    if(charge_number[0] != null) {
                        editTextsTraining[0].requestFocus();
                    } else if(charge_number[3] != null) {
                        editTextsTraining[3].requestFocus();
                    }

                    if(!image.isShown()){
//                        image.setScaleY((float) 0.95);
//                        image.setScaleX((float) 0.95);
                        image.setImageBitmap(bmapOverlay);
                    } else if(!image2.isShown()){
//                        image2.setScaleY((float) 0.95);
//                        image2.setScaleX((float) 0.95);
                        image2.setImageBitmap(bmapOverlay);
                    }
                    viewFlipper.showNext();
                }
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
