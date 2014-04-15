package com.logophone;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.nostra13.universalimageloader.cache.disc.impl.TotalSizeLimitedDiscCache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by mongOose on 22.12.13.
 */
public class Visualizer extends Activity{
    private static final int D_INFO = 1;
    private static final int D_CHOOSER = 2;
    private ViewFlipper viewFlipper, viewFlipperBack;
    private Context mContext = this;
    private ImageView image, image2, imgBack, imgBack2;
    private int TypeSize, showtype;
    private float lastX;
    private int screenWidth;
    private Integer phone_number[];
    private Integer charge_number[];
    private String filename[];
    private Bitmap bmapOverlay, bmapBackground;
    private boolean ThreadState = false;
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_info:
//                DialogManager(D_INFO);
//                return true;
//        }
//        return false;
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
    }

    private Dialog DialogManager(int dType){
        final Dialog dialog = new Dialog(mContext);//, R.style.myBackgroundStyle);
        switch (dType){
            case D_INFO:
                dialog.setContentView(R.layout.information_all);
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
                lvAll.setAdapter(new InfoListAdapter(mContext, rows[TypeSize-1], TypeSize));

                dialog.show();
                break;
            case D_CHOOSER:
                dialog.setContentView(R.layout.type_chooser);
                dialog.setTitle("Number of Elements:");
                dialog.setCancelable(false);
                String[] typeToChoose = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
                final Spinner dSpinner = (Spinner) dialog.findViewById(R.id.spinnerChooserType);

                Button bdExit = (Button) dialog.findViewById(R.id.btnChooserBack);
                bdExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        finish();
                        overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
                    }
                });
                Button bdSelect = (Button) dialog.findViewById(R.id.btnChooserSelect);
                bdSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TypeSize = Integer.parseInt(String.valueOf(dSpinner.getSelectedItem().toString()));
                        new ImageBuilder().execute();
                        dialog.dismiss();
                    }
                });
                ArrayAdapter<String> dSpinnerArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, typeToChoose);
                dSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dSpinner.setAdapter(dSpinnerArrayAdapter);

                dialog.show();
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_visualizer);
        DialogManager(D_CHOOSER);
        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);
        screenWidth = p.x;
        p.y = p.x*1528/1080;
//        p.x *= 0.975;
//        p.y *= 0.975;

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

        final Animation animAlpha = AnimationUtils.loadAnimation(mContext, R.anim.alpha);

        ImageButton ibRedo = (ImageButton)findViewById(R.id.imgBtnVisRedo);
        ibRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(animAlpha);
                DialogManager(D_CHOOSER);
            }
        });

        ImageButton ibNext = (ImageButton)findViewById(R.id.imgBtnNextImg);
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setAnimation(animAlpha);
                if(!ThreadState)
                    new ImageBuilder().execute();
            }
        });

        ImageButton info = (ImageButton) findViewById(R.id.imgBtnVisInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                DialogManager(D_INFO);
            }
        });

        viewFlipper = (ViewFlipper) findViewById(R.id.visViewFlipper);
        viewFlipper.setInAnimation(mContext, R.anim.in_from_right);
        viewFlipper.setOutAnimation(mContext, R.anim.out_to_left);
        viewFlipperBack = (ViewFlipper) findViewById(R.id.visViewBackgroundFlipper);
        viewFlipperBack.setInAnimation(mContext, R.anim.in_from_right);
        viewFlipperBack.setOutAnimation(mContext, R.anim.out_to_left);
        image = (ImageView)findViewById(R.id.visImage);
        image2 = (ImageView)findViewById(R.id.visImage2);
        imgBack = (ImageView)findViewById(R.id.imgVisBack);
        imgBack2 = (ImageView)findViewById(R.id.imgVisBack2);

    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();
                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    // Show The Previous Screen
                    if(!ThreadState)
                        new ImageBuilder().execute();
                }
                break;
        }
        return false;
    }

    private class imgVisBackBuilder extends AsyncTask<Integer, Integer, Boolean>{
        private Point p = new Point();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.y = viewFlipperBack.getHeight();
            p.x = p.y*1080/1528;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            ThreadState = false;
            if(aBoolean){
                if(phone_number.length != 0){
                    if(!imgBack.isShown()){
                        imgBack.setImageBitmap(bmapBackground);
                    } else if(!imgBack2.isShown()){
                        imgBack2.setImageBitmap(bmapBackground);
                    }
                    viewFlipperBack.showNext();
                }
            } else {
                if(bmapBackground != null){
                    if(!imgBack.isShown()){
                        imgBack.setImageResource(android.R.color.transparent);
                    } else if(!imgBack2.isShown()){
                        imgBack2.setImageResource(android.R.color.transparent);
                    }
                    viewFlipperBack.showNext();
                    bmapBackground.recycle();
                }
            }
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            boolean result = false;
            bmapBackground = Bitmap.createBitmap(screenWidth, p.y, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bmapBackground);
            Point p2 = new Point();
            p2.x = screenWidth;
            p2.y = p.y;
            filename[0] = "flag/40.png";
            switch (integers[0]){
                case 1:
                    addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], p2, true);
                    result = true;
                    break;
                case 2:
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[0], canvas, 0, p2, false);
                    addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], p, true);
                    result = true;
                    break;
                case 3:
                    Random random = new Random();
                    int type = random.nextInt(3-0) + 0;
                    switch (type){
                        case 0:
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            filename[1] = "figure/" + phone_number[2] + "00.png";
                            addLayoutToCanvas(filename[0], canvas, 0, p2, false);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], p, true);
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[0]], p, true);
                            break;
                        case 1:
                            filename[2] = "figure/" + phone_number[2] + "01.png";
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], p2, true);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[1]], p, true);
                            break;
                        case 2:
                            filename[0] = "flag/10.png";
                            filename[1] = "flag/20.png";
                            filename[2] = "flag/30.png";
                            p.x = screenWidth;
                            addLayoutToCanvas(filename[0], canvas, colors_array[phone_number[0]], p2, true);
                            addLayoutToCanvas(filename[1], canvas, colors_array[phone_number[1]], p2, true);
                            addLayoutToCanvas(filename[2], canvas, colors_array[phone_number[2]], p2, true);
                            break;
                        default:
                            break;
                    }
                    result = true;
                    break;
                default:
                    break;
            }
            return result;
        }
    }

    private class ImageBuilder extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ThreadState = true;
            phone_number = new Integer[10];
            charge_number = new Integer[10];
            filename = new String[10];
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
                            addLayoutToCanvas(filename[5], canvas, 0, p, false);
                            addLayoutToCanvas(filename[7], canvas, colors_array[aSorted[indexOfUzor] % 10], p, true);
                        } else if(i == indexOfUzor || (aSorted[i] == aSorted[indexOfUzor] && i == indexOfUzor)){
                            //do nothing
                        } else if(TypeSize > 6) {
                            filename[9] = (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i])) + "XXXX.png";
                            System.out.println(filename[9]);
                            addLayoutToCanvas(filename[9], canvas, 0, p, false);
                        }
                    }

                    break;
                case 1:
                    filename[5] = (String.valueOf(aZipperType[0]).length() > 4 ? String.valueOf(aZipperType[0]).substring(1) : String.valueOf(aZipperType[0])) + "XXXX.png";
                    filename[7] = "uzors/" + (String.valueOf(aZipperType[0]).length() > 4 ? String.valueOf(aZipperType[0]).substring(1, 4) : String.valueOf(aZipperType[0]).substring(0, 3))
                            + "X" + (String.valueOf(aZipperType[1]).length() > 4 ? String.valueOf(aZipperType[1]).substring(2, 3) : String.valueOf(aZipperType[1]).substring(1, 2)) + "0XX.png";
                    System.out.println(filename[5]);
                    System.out.println(filename[7]);
                    addLayoutToCanvas(filename[5], canvas, 0, p, false);
                    addLayoutToCanvas(filename[7], canvas, colors_array[aZipperType[1] % 10], p, true);
                    if(TypeSize > 6) {
                        filename[9] = "glasses/" + (phone_number[3] == 10 ? 0 : phone_number[3]) + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                        System.out.println(filename[9]);
                        addLayoutToCanvas(filename[9], canvas, 0, p, false);
                    }
                    break;
                default:
                    break;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            new imgVisBackBuilder().execute(values[0]);
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_4444);
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
                        onProgressUpdate(0);
                        charge_number[3] = phone_number[3];
                        addLayoutToCanvas(filename[3], canvas, 0, p, false);
                        //          char
                    } else {
                        charge_number[0] = phone_number[0];
                        onProgressUpdate(1);
                        //          background
                    }
                    break;
                case 2:
                    charge_number[1] = phone_number[1];
                    charge_number[2] = phone_number[2];
                    onProgressUpdate(2);
                    break;
                case 3:
                    type = rand.nextInt(2-0) + 0;
                    switch (type){
                        case 0:
                            onProgressUpdate(0);
                            charge_number[3] = phone_number[3];
                            charge_number[4] = phone_number[4];
                            charge_number[5] = phone_number[5];
                            filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                            addLayoutToCanvas(filename[3], canvas, 0, p, false);
                            addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, p, false);
                            //          char
                            break;
                        case 1:
                            charge_number[0] = phone_number[0];
                            charge_number[1] = phone_number[1];
                            charge_number[2] = phone_number[2];
                            onProgressUpdate(3);
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
                    onProgressUpdate(1);
                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
                    addLayoutToCanvas(filename[5] + "XXXX.png", canvas, 0, p, false);
                    break;
                case 5:
                    onProgressUpdate(0);
                    for(int i = 3; i < phone_number.length - 2; i++)
                        charge_number[i] = phone_number[i];

                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 6:
                    charge_number[0] = phone_number[0];
                    for(int i = 3; i < phone_number.length - 2; i++)
                        charge_number[i] = phone_number[i];

                    filename[5] = phone_number[3] + "" + phone_number[5] + "0" + phone_number[4];
                    filename[7] = "uzors/" + phone_number[3] + "" + phone_number[5] + "0X" + phone_number[7] + "0XX.png";
                    onProgressUpdate(1);
                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 7:
                    onProgressUpdate(0);
                    for(int i = 3; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];

                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 8:
                    charge_number[0] = phone_number[0];
                    for(int i = 3; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];
                    onProgressUpdate(1);
                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 9:
                    for(int i = 1; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];
                    onProgressUpdate(2);
                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
                    setTrueNumeric(aSorted, aZipperType, canvas, indexOfClothes, indexOfUzor);
                    break;
                case 10:
                    for(int i = 0; i < phone_number.length; i++)
                        charge_number[i] = phone_number[i];
                    onProgressUpdate(3);
                    addLayoutToCanvas(filename[3], canvas, 0, p, false);
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

                    if(!image.isShown()){
                        image.setImageBitmap(bmapOverlay);
                    } else if(!image2.isShown()){
                        image2.setImageBitmap(bmapOverlay);
                    }
                    viewFlipper.showNext();
                }
            }
        }
    }

    public static Bitmap lessResolution (InputStream filePath, int width, int height){
        int reqHeight=width;
        int reqWidth=height;

        byte[] byteArr = new byte[0];
        byte[] buffer = new byte[1024];
        int len;
        int count = 0;
        try {
            while ((len = filePath.read(buffer)) > -1) {
                if (len != 0) {
                    if (count + len > byteArr.length) {
                        byte[] newbuf = new byte[(count + len) * 2];
                        System.arraycopy(byteArr, 0, newbuf, 0, count);
                        byteArr = newbuf;
                    }

                    System.arraycopy(buffer, 0, byteArr, count, len);
                    count += len;
                }
            }
            BitmapFactory.Options options = new BitmapFactory.Options();

            // First decode with inJustDecodeBounds=true to check dimensions
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(byteArr, 0, count, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;

            return BitmapFactory.decodeByteArray(byteArr, 0, count, options);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
//        System.out.println("s.w/s.h: " + reqWidth + "/" + reqHeight);
//        System.out.println("p.w/p.h: " + options.outWidth + "/" + options.outHeight);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
//            System.out.println("Ratio values: " + widthRatio + "/" + heightRatio);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
//        System.out.println("Scale value: " + inSampleSize);
        return inSampleSize;
    }

    private void addLayoutToCanvas(String filename, Canvas canvas, int color, Point p, boolean isFill){
        try {
            InputStream is = getAssets().open("raw/" + filename);
//            Bitmap bMap = BitmapFactory.decodeStream(is);
            Bitmap rbMap = lessResolution(is, p.x, p.y);
//            Bitmap rbMap = Bitmap.createScaledBitmap(bMap, p.x, p.y, true);

//            bMap.recycle();
            Paint paint = new Paint();
//            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            if(isFill){
                paint.setColor(color);
//                paint.setDither(true);
                ColorFilter filter = new LightingColorFilter(color, 1);
                paint.setColorFilter(filter);
            }
            canvas.drawBitmap(rbMap, null, new Rect(0, 0, p.x, p.y), paint);
//            canvas.drawBitmap(rbMap, new Matrix(), paint);
            rbMap.recycle();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
