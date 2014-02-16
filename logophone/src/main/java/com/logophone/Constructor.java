package com.logophone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by mongOose on 11.11.13.
 */
public class Constructor extends Activity {
    private ImageView image, image2, imgBackground, imgBackground2;
    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10;
    private static final int PICK_CONTACT = 3245;
    private ProgressDialog mProgressDialog, SaveProgressDialog;
    private Integer phone_number[] = {};
    private ViewFlipper vFlip, vFlipMain;
    private float lastX, lastY;
    private int background_round, showtype, currentShowType;
    private Context mContext = this;
    private Point p = new Point();
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

    private Bitmap bmapOverlay, bmapBackground;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_logo_by_number);
        ImageButton ibGenerate, ibContacts, ibShare, ibSave;

        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.x = disp.getWidth();
            p.y = disp.getHeight();
        } else
            disp.getSize(p);
        p.y = p.x*1528/1080;

        et1 = (EditText)findViewById(R.id.editText1);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et1.getText().toString().length() > 0)
                    et2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2 = (EditText)findViewById(R.id.editText2);
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et2.getText().toString().length() > 0)
                    et3.requestFocus();
                else if(et2.getText().toString().length() < 1)
                    et1.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et3 = (EditText)findViewById(R.id.editText3);
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et3.getText().toString().length() > 0)
                    et4.requestFocus();
                else if(et3.getText().toString().length() < 1)
                    et2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et4 = (EditText)findViewById(R.id.editText4);
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et4.getText().toString().length() > 0)
                    et5.requestFocus();
                else if(et4.getText().toString().length() < 1)
                    et3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et5 = (EditText)findViewById(R.id.editText5);
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et5.getText().toString().length() > 0)
                    et6.requestFocus();
                else if(et5.getText().toString().length() < 1)
                    et4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et6 = (EditText)findViewById(R.id.editText6);
        et6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et6.getText().toString().length() > 0)
                    et7.requestFocus();
                else if(et6.getText().toString().length() < 1)
                    et5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et7 = (EditText)findViewById(R.id.editText7);
        et7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et7.getText().toString().length() > 0)
                    et8.requestFocus();
                else if(et7.getText().toString().length() < 1)
                    et6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et8 = (EditText)findViewById(R.id.editText8);
        et8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et8.getText().toString().length() > 0)
                    et9.requestFocus();
                else if(et8.getText().toString().length() < 1)
                    et7.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et9 = (EditText)findViewById(R.id.editText9);
        et9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et9.getText().toString().length() > 0)
                    et10.requestFocus();
                else if(et9.getText().toString().length() < 1)
                    et8.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et10 = (EditText)findViewById(R.id.editText10);
        et10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if(et10.getText().toString().length() < 1)
                    et9.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        vFlip = (ViewFlipper) findViewById(R.id.ConstructorViewFlipperBackground);
        vFlip.setOutAnimation(mContext, R.anim.out_to_left);
        vFlip.setInAnimation(mContext, R.anim.in_from_right);
        vFlipMain = (ViewFlipper) findViewById(R.id.ConstructorViewFlipperMain);
        vFlipMain.setOutAnimation(mContext, R.anim.out_to_left);
        vFlipMain.setInAnimation(mContext, R.anim.in_from_right);
        image = (ImageView)findViewById(R.id.imgCreateLogo);
        image2 = (ImageView)findViewById(R.id.imgCreateLogo2);
        imgBackground = (ImageView)findViewById(R.id.imgCreateBackground);
        imgBackground2 = (ImageView)findViewById(R.id.imgCreateBackground2);

        Toast.makeText(mContext, "W: " + p.x + "\nH: " + p.y, Toast.LENGTH_SHORT).show();

        ibSave = (ImageButton)findViewById(R.id.imgBtnSave);
        ibSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SaveImage().execute();
            }
        });

        ibShare = (ImageButton)findViewById(R.id.imgBtnShare);
        ibShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(android.content.Intent.EXTRA_TEXT, "Img");
                startActivity(Intent.createChooser(intentShare, "Отправить логотип:"));
            }
        });

        ibContacts = (ImageButton)findViewById(R.id.imgBtnContacts);
        ibContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iPick = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(iPick, PICK_CONTACT);
            }
        });
        ibGenerate = (ImageButton)findViewById(R.id.imgBtnCreate);
        ibGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = et1.getText() + "" + et2.getText() + "" + et3.getText() + "" + et4.getText() + "" + et5.getText() + "" +
                        et6.getText() + "" + et7.getText() + "" + et8.getText() + "" + et9.getText() + "" + et10.getText();
                phone_number = convert(tel);
                Random rand = new Random();
                int type = rand.nextInt(3-0) + 0;
                currentShowType = 0;
                new CreateImageFromContact().execute();
                new CreateImageBackground().execute(type);
            }
        });
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
                        phone_number = convert(phone.replaceAll("\\D+", ""));
                        Random rand = new Random();
                        int type = rand.nextInt(3-0) + 0;
                        currentShowType = 0;
                        new CreateImageFromContact().execute();
                        new CreateImageBackground().execute(type);
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

        return number;
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

    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                lastY = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (phone_number != null){
                    if(lastY < p.y / 2)  {
                        if (lastX < currentX)
                        {
                            // set the required Animation type to ViewFlipper
                            // The Next screen will come in form Left and current Screen will go OUT from Right
                            vFlip.setOutAnimation(mContext, R.anim.out_to_right);
                            vFlip.setInAnimation(mContext, R.anim.in_from_left);
                            // Show the next Screen
                            new CreateImageBackground().execute(background_round > 0 ? background_round-1 : 2);
                        } else if (lastX > currentX) {
                            // set the required Animation type to ViewFlipper
                            // The Next screen will come in form Right and current Screen will go OUT from Left
                            vFlip.setOutAnimation(mContext, R.anim.out_to_left);
                            vFlip.setInAnimation(mContext, R.anim.in_from_right);
                            // Show The Previous Screen
                            new CreateImageBackground().execute(background_round < 2 ? background_round+1 : 0);
                        }
                    } else {
                        if (lastX < currentX)
                        {
                            // set the required Animation type to ViewFlipper
                            // The Next screen will come in form Left and current Screen will go OUT from Right
                            vFlipMain.setOutAnimation(mContext, R.anim.out_to_right);
                            vFlipMain.setInAnimation(mContext, R.anim.in_from_left);
                            // Show the next Screen
                            new CreateImageFromContact().execute(currentShowType > 1 ? currentShowType-1 : showtype);
                        } else if (lastX > currentX) {
                            // set the required Animation type to ViewFlipper
                            // The Next screen will come in form Right and current Screen will go OUT from Left
                            vFlipMain.setOutAnimation(mContext, R.anim.out_to_left);
                            vFlipMain.setInAnimation(mContext, R.anim.in_from_right);
                            // Show The Previous Screen
                            new CreateImageFromContact().execute(currentShowType < showtype ? currentShowType+1 : 1);
                        }
                    }
                }
                break;
            }
        }
        return false;
    }

    private class SaveImage extends AsyncTask<Integer, Integer, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SaveProgressDialog = new ProgressDialog(mContext);
            SaveProgressDialog.setCancelable(false);
            SaveProgressDialog.setMessage("Saving image to card...");
            SaveProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            SaveProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            SaveProgressDialog.dismiss();
            if(aBoolean)
                Toast.makeText(mContext, "Image was saved!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(mContext, "Cannot save image!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            boolean result = false;
            String filename = String.valueOf(System.currentTimeMillis()) + ".jpg";
            File sd = Environment.getExternalStorageDirectory();
            File dest = new File(sd, filename);
            Bitmap bmpToSave = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas saveCanvas = new Canvas(bmpToSave);
            saveCanvas.drawBitmap(bmapBackground, new Matrix(), null);
            saveCanvas.drawBitmap(bmapOverlay, new Matrix(), null);
            try{
                FileOutputStream out = new FileOutputStream(dest);
                bmpToSave.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                result = true;
            } catch (IOException e){
                e.printStackTrace();
            }
            return result;
        }
    }

    private class CreateImageBackground extends AsyncTask<Integer, Integer, Boolean>{
        @Override
        protected Boolean doInBackground(Integer... integers) {
            if(phone_number.length != 10) return false;
            bmapBackground = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas bCanvas = new Canvas(bmapBackground);
            String[] filename = new String[3];
            background_round = integers[0];
            switch (integers[0]){
                case 0:
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], bCanvas, colors_array[phone_number[0]], Color.WHITE, true);
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[2], bCanvas, colors_array[phone_number[1]], Color.WHITE, true);
                    break;
                case 1:
                    filename[0] = "flag/10.png";
                    addLayoutToCanvas(filename[0], bCanvas, colors_array[phone_number[0]], Color.WHITE, true);
                    filename[1] = "flag/20.png";
                    addLayoutToCanvas(filename[1], bCanvas, colors_array[phone_number[1]], Color.WHITE, true);
                    filename[2] = "flag/30.png";
                    addLayoutToCanvas(filename[2], bCanvas, colors_array[phone_number[2]], Color.WHITE, true);
                    break;
                case 2:
                    filename[2] = "figure/" + phone_number[2] + "01.png";
                    addLayoutToCanvas(filename[2], bCanvas, colors_array[phone_number[1]], Color.WHITE, true);
                    filename[1] = "figure/" + phone_number[2] + "00.png";
                    addLayoutToCanvas(filename[1], bCanvas, colors_array[phone_number[0]], Color.WHITE, true);
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
                if(!imgBackground.isShown())
                    imgBackground.setImageBitmap(bmapBackground);
                else if(!imgBackground2.isShown())
                    imgBackground2.setImageBitmap(bmapBackground);
                vFlip.showNext();
            }
        }
    }

    private class CreateImageFromContact extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Creating logo...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean doCreate) {
            super.onPostExecute(doCreate);
            mProgressDialog.dismiss();
            if(doCreate){
                if(phone_number.length != 0){
                    et1.setText(String.valueOf(phone_number[0]));
                    et1.setBackgroundColor(Color.LTGRAY);
                    et1.setFocusable(false);
                    et2.setText(String.valueOf(phone_number[1]));
                    et2.setBackgroundColor(Color.LTGRAY);
                    et2.setFocusable(false);
                    et3.setText(String.valueOf(phone_number[2]));
                    et3.setBackgroundColor(Color.LTGRAY);
                    et3.setFocusable(false);
                    et4.setText(String.valueOf(phone_number[3] == 10 ? 0 :phone_number[3]));
                    et4.setBackgroundColor(Color.LTGRAY);
                    et4.setFocusable(false);
                    et5.setText(String.valueOf(phone_number[4]));
                    et5.setBackgroundColor(Color.LTGRAY);
                    et5.setFocusable(false);
                    et6.setText(String.valueOf(phone_number[5]));
                    et6.setBackgroundColor(Color.LTGRAY);
                    et6.setFocusable(false);
                    et7.setText(String.valueOf(phone_number[6]));
                    et7.setBackgroundColor(Color.LTGRAY);
                    et7.setFocusable(false);
                    et8.setText(String.valueOf(phone_number[7]));
                    et8.setBackgroundColor(Color.LTGRAY);
                    et8.setFocusable(false);
                    et9.setText(String.valueOf(phone_number[8]));
                    et9.setBackgroundColor(Color.LTGRAY);
                    et9.setFocusable(false);
                    et10.setText(String.valueOf(phone_number[9]));
                    et10.setBackgroundColor(Color.LTGRAY);
                    et10.setFocusable(false);

                    if(!image.isShown()){
//                        Toast.makeText(mContext, "Img 1 shown: " + showtype, Toast.LENGTH_LONG).show();
                        image.setScaleY((float) 0.95);
                        image.setScaleX((float) 0.95);
                        image.setImageBitmap(bmapOverlay);
                    } else if(!image2.isShown()){
//                        Toast.makeText(mContext, "Img 2 shown: " + showtype, Toast.LENGTH_LONG).show();
                        image2.setScaleY((float) 0.95);
                        image2.setScaleX((float) 0.95);
                        image2.setImageBitmap(bmapOverlay);
                    }
                    vFlipMain.showNext();

                }
            } else {
                Toast.makeText(mContext, "Bad phone number!", Toast.LENGTH_LONG).show();
            }
        }

        private int indexOfIntArray(int[] array, int key) {
            int returnvalue = -1;
            for (int i = 0; i < array.length; i++) {
                if (key == array[i]) {
                    returnvalue = i;
                    break;
                }
            }
            return returnvalue;
        }

        @Override
        protected Boolean doInBackground(Integer... ints) {
            if(phone_number.length != 10) return false;
            bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmapOverlay);

            String[] filename = new String[10];
            Creator check = new Creator(phone_number);

            int[] aZipperType = check.GetZipperType();

            if(ints.length == 0) {
                showtype = 0;
                if(check.newCheckNoStrict()) showtype = 3;
                else if(check.newCheckPleasure()) showtype = 2;
                else if(check.newCheckStrict()) showtype = 1;
                currentShowType = showtype;
            } else {
                currentShowType = ints[0];
            }

//_______________________CHARACTER__________________________________________________________________
            filename[3] = (phone_number[3] == 10 ? 0 : phone_number[3]) + "XXXXXXX.png";
            addLayoutToCanvas(filename[3], canvas, 0, 0, false);

            int[] aSorted = check.SortClothes(aZipperType);
            System.out.println(Arrays.toString(aSorted) + " == " + Arrays.toString(aZipperType));
            switch (currentShowType){
                case 3:
                    for( int file : aSorted ){
//                        System.out.println(String.valueOf(file).length() > 4 ? String.valueOf(file).substring(1) : String.valueOf(file) + "XXXX.png");
                        addLayoutToCanvas((String.valueOf(file).length() > 4 ? String.valueOf(file).substring(1) : String.valueOf(file)) + "XXXX.png", canvas, 0, 0, false);
                    }
                    break;
                case 2:
                    int indexOfClothes = indexOfIntArray(aSorted, aZipperType[0]);
                    int indexOfUzor = indexOfIntArray(aSorted, aZipperType[1]);
                    System.out.println(indexOfClothes + "\n" + indexOfUzor);
                    for(int i = 0; i < aSorted.length; i++){
                        if(i == indexOfClothes){
//                            System.out.println(String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i]) + "XXXX.png");
                            addLayoutToCanvas((String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i])) + "XXXX.png", canvas, 0, 0, false);
//                            System.out.println("uzors/" + (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1, 4) : String.valueOf(aSorted[i]).substring(0, 3))
//                                    + "X" + (String.valueOf(aSorted[indexOfUzor]).length() > 4 ? String.valueOf(aSorted[indexOfUzor]).substring(2, 3) : String.valueOf(aSorted[indexOfUzor]).substring(1, 2))
//                                    + "0XX.png");
                            addLayoutToCanvas("uzors/" + (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1, 4) : String.valueOf(aSorted[i]).substring(0, 3))
                                    + "X" + (String.valueOf(aSorted[indexOfUzor]).length() > 4 ? String.valueOf(aSorted[indexOfUzor]).substring(2, 3) : String.valueOf(aSorted[indexOfUzor]).substring(1, 2))
                                    + "0XX.png", canvas, colors_array[aSorted[indexOfUzor] % 10], colors_array[9], true);
                        } else if(i == indexOfUzor){
                            //do nothing
                        } else{
//                            System.out.println(String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i]) + "XXXX.png");
                            addLayoutToCanvas((String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i])) + "XXXX.png", canvas, 0, 0, false);
                        }
                    }

                    break;
                case 1:
                    filename[5] = (String.valueOf(aZipperType[0]).length() > 4 ? String.valueOf(aZipperType[0]).substring(1) : String.valueOf(aZipperType[0])) + "XXXX.png";
                    filename[7] = "uzors/" + (String.valueOf(aZipperType[0]).length() > 4 ? String.valueOf(aZipperType[0]).substring(1, 4) : String.valueOf(aZipperType[0]).substring(0, 3))
                            + "X" + (String.valueOf(aZipperType[1]).length() > 4 ? String.valueOf(aZipperType[1]).substring(2, 3) : String.valueOf(aZipperType[1]).substring(1, 2)) + "0XX.png";
                    filename[9] = "glasses/" + (phone_number[3] == 10 ? 0 : phone_number[3]) + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                    addLayoutToCanvas(filename[5], canvas, 0, 0, false);
                    addLayoutToCanvas(filename[7], canvas, colors_array[aZipperType[1] % 10], colors_array[9], true);
                    addLayoutToCanvas(filename[9], canvas, 0, 0, false);
//                    System.out.println(filename[5]);
//                    System.out.println(filename[7]);
//                    System.out.println(filename[9]);
                    break;
                default:
                    break;
            }
            publishProgress();
            return true;
        }
    }
}


