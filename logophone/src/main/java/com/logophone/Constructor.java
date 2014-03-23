package com.logophone;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.ByteArrayOutputStream;
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
    private ImageButton ibLeftB, ibRightB, ibLeftM, ibRightM;
    private TextView tHelloCreator;
    private CheckBox chBxAddToContact;
    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, etNumber, etFName, etLName;
    private static final int D_INFO = 1;
    private static final int D_GET_NUMBER = 2;
    private static final int D_SAVE_IMAGE = 3;

    private static final int PICK_CONTACT = 3245;
    private ProgressDialog mProgressDialog, SaveProgressDialog, SendProgressDialog;
    private Integer phone_number[] = new Integer[]{};
    private ViewFlipper vFlip, vFlipMain;
    private int background_round, showtype, currentShowType;
    private long currentID;
    private String currentName;
    private Context mContext = this;
    private Point p = new Point();
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

    private Bitmap bmapOverlay, bmapBackground;

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

    private Dialog DialogManager(int dType){
        final Dialog dialog = new Dialog(mContext, R.style.myBackgroundStyle);
        final Drawable xD = this.getResources().getDrawable(R.drawable.cancel32);
        final Drawable xDC = this.getResources().getDrawable(R.drawable.contacts32);
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
                lvAll.setAdapter(new InfoListAdapter(mContext, rows[9], 10));

                dialog.show();
                break;
            case D_SAVE_IMAGE:
                dialog.setContentView(R.layout.dialog_save_image);
                dialog.setTitle("Number: " + Arrays.toString(phone_number).replaceAll("[^0-9]+", ""));

                etFName = (EditText) dialog.findViewById(R.id.etFirstNameSave);
                etLName = (EditText) dialog.findViewById(R.id.etLastNameSave);
                etFName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if(charSequence.length() > 0)
                            etFName.setCompoundDrawablesWithIntrinsicBounds(null, null, xD, null);
                        else etNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                etFName.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            boolean tappedX = event.getX() > (view.getWidth()
                                    - view.getPaddingRight() - xD.getIntrinsicWidth());
                            if (tappedX) {
                                if(etFName.getText().toString().length() > 0){
                                    etFName.setText("");
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });
                etLName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if(charSequence.length() > 0)
                            etLName.setCompoundDrawablesWithIntrinsicBounds(null, null, xD, null);
                        else etNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                etLName.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            boolean tappedX = event.getX() > (view.getWidth()
                                    - view.getPaddingRight() - xD.getIntrinsicWidth());
                            if (tappedX) {
                                if(etLName.getText().toString().length() > 0){
                                    etLName.setText("");
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });
                if(currentID != 0){
                    String names[] = currentName.split(" ");
                    etFName.setText(names.length > 0 ? names[0] : "");
                    etLName.setText(names.length > 1 ? names[1] : "");
                }
                Button bSave = (Button) dialog.findViewById(R.id.btnSaveDialogSave);
                bSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new SaveImage().execute("_" + etFName.getText().toString(), "_" + etLName.getText().toString(), chBxAddToContact.isChecked() ? "1" : "0");
                        InputMethodManager imm = (InputMethodManager)getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(etLName.getWindowToken(), 0);
                        dialog.dismiss();
                    }
                });
                Button bSaveBack = (Button) dialog.findViewById(R.id.btnSaveDialogBack);
                bSaveBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(etLName.getWindowToken(), 0);
                        dialog.dismiss();
                    }
                });
                chBxAddToContact = (CheckBox) dialog.findViewById(R.id.checkBoxAddContactImage);
                if(currentID != 0)
                    chBxAddToContact.setEnabled(true);
                else
                    chBxAddToContact.setEnabled(false);

                dialog.show();
                break;
            case D_GET_NUMBER:
                dialog.setContentView(R.layout.dialog_get_number);
                dialog.setTitle("Set Number to roll:");
                dialog.setCancelable(false);
                currentID = 0;
                currentName = null;

                etNumber = (EditText)dialog.findViewById(R.id.editDialogGetNumber);
                etNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, xDC, null);
                etNumber.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if(charSequence.length() > 0)
                            etNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, xD, null);
                        else etNumber.setCompoundDrawablesWithIntrinsicBounds(null, null, xDC, null);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                etNumber.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            boolean tappedX = event.getX() > (view.getWidth()
                                    - view.getPaddingRight() - xD.getIntrinsicWidth());
                            if (tappedX) {
                                if(etNumber.getText().toString().length() > 0){
                                    etNumber.setText("");
                                    currentID = 0;
                                    currentName = null;
                                } else {
                                    Intent iPick = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                                    startActivityForResult(iPick, PICK_CONTACT);
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });
                Button bBack = (Button)dialog.findViewById(R.id.btnDialogGetNumberExit);
                bBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        finish();
                    }
                });
                Button bGo = (Button)dialog.findViewById(R.id.btnDialogGetNumberGo);
                bGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String tel = String.valueOf(etNumber.getText());
                        phone_number = convert(tel.replaceAll("[^0-9]+", ""));
                        Random rand = new Random();
//                        int type = rand.nextInt(3-0) + 0;
                        currentShowType = 0;
//                        currentID = 0;
                        new CreateImageFromContact().execute();
                        new CreateImageBackground().execute(0);
                        InputMethodManager imm = (InputMethodManager)getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(etNumber.getWindowToken(), 0);
                        mProgressDialog = new ProgressDialog(mContext);
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.setMessage("Creating logo...");
                        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        mProgressDialog.show();
                        dialog.dismiss();
                    }
                });

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
        setContentView(R.layout.create_logo_by_number);
        DialogManager(D_GET_NUMBER);
        ImageButton ibGenerate, ibContacts, ibShare, ibSave, ibDelete, ibInfo;

        currentID = 0;
        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            p.y = disp.getWidth();
            p.x = disp.getHeight();
        } else
            disp.getSize(p);
        p.y = p.x*1528/1080;
//        p.x *= 0.75;
//        p.y *= 0.75;

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

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

        tHelloCreator = (TextView)findViewById(R.id.txtHelloCreator);

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

        ibInfo = (ImageButton) findViewById(R.id.imgBtnInfo);
        ibInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                DialogManager(D_INFO);
            }
        });

//        ibDelete = (ImageButton)findViewById(R.id.imgBtnRecycle);
//        ibDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(animAlpha);
//                et1.setText(null);
//                et1.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et1.setFocusableInTouchMode(true);
//                et2.setText(null);
//                et2.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et2.setFocusableInTouchMode(true);
//                et3.setText(null);
//                et3.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et3.setFocusableInTouchMode(true);
//                et4.setText(null);
//                et4.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et4.setFocusableInTouchMode(true);
//                et5.setText(null);
//                et5.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et5.setFocusableInTouchMode(true);
//                et6.setText(null);
//                et6.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et6.setFocusableInTouchMode(true);
//                et7.setText(null);
//                et7.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et7.setFocusableInTouchMode(true);
//                et8.setText(null);
//                et8.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et8.setFocusableInTouchMode(true);
//                et9.setText(null);
//                et9.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et9.setFocusableInTouchMode(true);
//                et10.setText(null);
//                et10.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
//                et10.setFocusableInTouchMode(true);
//                et1.requestFocus();
//
//                ibLeftM.setVisibility(View.GONE);
//                ibRightM.setVisibility(View.GONE);
//                ibLeftB.setVisibility(View.GONE);
//                ibRightB.setVisibility(View.GONE);
//                tHelloCreator.setVisibility(View.VISIBLE);
//
//                currentName = null;
//                currentID = 0;
//                phone_number = new Integer[]{};
//                if(bmapBackground != null) {
//                    bmapBackground.recycle();
//                    imgBackground.setImageResource(android.R.color.transparent);
//                    imgBackground2.setImageResource(android.R.color.transparent);
//                }
//                if(bmapOverlay != null) {
//                    bmapOverlay.recycle();
//                    image.setImageResource(android.R.color.transparent);
//                    image2.setImageResource(android.R.color.transparent);
//                }
//            }
//        });

        ibSave = (ImageButton)findViewById(R.id.imgBtnSave);
        ibSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                if(phone_number.length == 10 && bmapOverlay != null)
                    DialogManager(D_SAVE_IMAGE);
                else
                    Toast.makeText(mContext, "Can't Save Logo!", Toast.LENGTH_SHORT).show();
            }
        });
        ibShare = (ImageButton)findViewById(R.id.imgBtnShare);
        ibShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                if(phone_number.length == 10 && bmapOverlay != null)
                    new SendImage().execute();
                else
                    Toast.makeText(mContext, "Can't Share Logo!", Toast.LENGTH_SHORT).show();
            }
        });

        ibContacts = (ImageButton)findViewById(R.id.imgBtnContacts);
        ibContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                DialogManager(D_GET_NUMBER);
            }
        });

        ibLeftB = (ImageButton)findViewById(R.id.imgBtnLeftBack);
        ibLeftB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                if(phone_number.length == 10){
                    vFlip.setOutAnimation(mContext, R.anim.out_to_right);
                    vFlip.setInAnimation(mContext, R.anim.in_from_left);
                    // Show the next Screen
                    new CreateImageBackground().execute(background_round > 0 ? background_round-1 : 2);
                }
            }
        });
        ibRightB = (ImageButton)findViewById(R.id.imgBtnRightBack);
        ibRightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                if(phone_number.length == 10){
                    vFlip.setOutAnimation(mContext, R.anim.out_to_left);
                    vFlip.setInAnimation(mContext, R.anim.in_from_right);
                    // Show The Previous Screen
                    new CreateImageBackground().execute(background_round < 2 ? background_round+1 : 0);
                }
            }
        });

        ibLeftM = (ImageButton)findViewById(R.id.imgBtnLeftMain);
        ibLeftM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                if(phone_number.length == 10){
                    vFlipMain.setOutAnimation(mContext, R.anim.out_to_right);
                    vFlipMain.setInAnimation(mContext, R.anim.in_from_left);
                    // Show the next Screen
                    if(showtype != 1)
                        new CreateImageFromContact().execute(currentShowType > 1 ? currentShowType-1 : showtype);
                }
            }
        });
        ibRightM = (ImageButton)findViewById(R.id.imgBtnRightMain);
        ibRightM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
                if(phone_number.length == 10){
                    vFlipMain.setOutAnimation(mContext, R.anim.out_to_left);
                    vFlipMain.setInAnimation(mContext, R.anim.in_from_right);
                    // Show The Previous Screen
                    if(showtype != 1)
                        new CreateImageFromContact().execute(currentShowType < showtype ? currentShowType+1 : 1);
                }
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
                        currentName = (String) c.getString(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        currentID = (long) c.getInt(c.getColumnIndexOrThrow(ContactsContract.Data.RAW_CONTACT_ID));
                        String num = phone.replaceAll("[^0-9]+", "");
                        etNumber.setText(num.substring(num.length() - 10));
//                        etNumber.setText("" + convert(phone.replaceAll("[^0-9]+", "")));
//                        Toast.makeText(mContext, "Person ID: " + currentID, Toast.LENGTH_SHORT).show();
//                        phone_number = convert(phone.replaceAll("\\D+", ""));
//                        Random rand = new Random();
//                        int type = rand.nextInt(3-0) + 0;
//                        currentShowType = 0;
//                        new CreateImageFromContact().execute();
//                        new CreateImageBackground().execute(type);
                    }
                    c.close();
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
//
//    private Bitmap resizeBitmap(Bitmap bbMap, int w, int h){
//        Bitmap rbMap = Bitmap.createScaledBitmap(bbMap, w, h, true);
////        Bitmap rbMap = Bitmap.createBitmap(bbMap);
//        bbMap.recycle();
////        if(replaceColor != 0){
////            rbMap.copy(Bitmap.Config.ARGB_8888, true);
////            int lenght = rbMap.getWidth()*rbMap.getHeight();
////            int[] pixels = new int[lenght];
////            rbMap.getPixels(pixels, 0, rbMap.getWidth(), 0, 0, rbMap.getWidth(), rbMap.getHeight());
////            for(int i=0; i < lenght; ++i){
////                int y = i/rbMap.getWidth();
////                int x = i - (y*rbMap.getWidth());
////                int pixel = rbMap.getPixel(x, y);
////                if(pixel == searchColor){
////                    rbMap.setPixel(x, y, replaceColor);
////                } else if(pixel == replaceColor){
////                    rbMap.setPixel(x, y, searchColor);
////                }
////
////            }
////        }
//        return rbMap;
//    }

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

    private void addLayoutToCanvas(String filename, Canvas canvas, int color, int changeColor, boolean isFill){
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

    public void setContactPhoto(byte[] bytes, long personId) {
        ContentValues values = new ContentValues();
        int photoRow = -1;
        String where = ContactsContract.Data.RAW_CONTACT_ID + " = " + personId + " AND " + ContactsContract.Data.MIMETYPE + "=='" + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'";
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, where, null, null);
        int idIdx = cursor.getColumnIndexOrThrow(ContactsContract.Data._ID);
        if (cursor.moveToFirst()) {
            photoRow = cursor.getInt(idIdx);
        }
        cursor.close();

        values.put(ContactsContract.Data.RAW_CONTACT_ID, personId);
        values.put(ContactsContract.Data.IS_SUPER_PRIMARY, 1);
        values.put(ContactsContract.CommonDataKinds.Photo.PHOTO, bytes);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);

        if (photoRow >= 0) {
            mContext.getContentResolver().update(ContactsContract.Data.CONTENT_URI, values, ContactsContract.Data._ID + " = " + photoRow, null);
        } else {
            mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }
    }

    private class SendImage extends AsyncTask<Integer, Integer, Boolean>{
        private Intent intentShare;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SendProgressDialog = new ProgressDialog(mContext);
            SendProgressDialog.setCancelable(false);
            SendProgressDialog.setMessage("Prepare for sharing...");
            SendProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            SendProgressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            boolean result = false;
            intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("image/jpeg");
            Bitmap bmpToSend = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas saveCanvas = new Canvas(bmpToSend);
            saveCanvas.drawBitmap(bmapBackground, new Matrix(), null);
            saveCanvas.drawBitmap(bmapOverlay, new Matrix(), null);

            try {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                bmpToSend.compress(Bitmap.CompressFormat.PNG, 90, bOut);
                File ftmp = File.createTempFile("sharedImage", ".jpg", getExternalCacheDir());
                FileOutputStream fOut = new FileOutputStream(ftmp);
                fOut.write(bOut.toByteArray());
                bOut.flush();
                bOut.close();
                fOut.flush();
                fOut.close();
                intentShare.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(ftmp));
                result = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            SendProgressDialog.dismiss();
            if(aBoolean){
                startActivity(Intent.createChooser(intentShare, "Send logo:"));
            } else {
                Toast.makeText(mContext, "Cannot add image to contact!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class SaveImage extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            SaveProgressDialog = new ProgressDialog(mContext);
            SaveProgressDialog.setCancelable(false);
            SaveProgressDialog.setMessage("Adding image to contact...");
            SaveProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            SaveProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            SaveProgressDialog.dismiss();
            if(aBoolean){
                Toast.makeText(mContext, "Image was saved to /sdcard/LogophoneContacts!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(mContext, "Cannot save image!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(String... str) {

            boolean result = false;
            Bitmap bmpToSave = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
            Canvas saveCanvas = new Canvas(bmpToSave);
            saveCanvas.drawBitmap(bmapBackground, new Matrix(), null);
            saveCanvas.drawBitmap(bmapOverlay, new Matrix(), null);
            try {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                bmpToSave.compress(Bitmap.CompressFormat.JPEG, 100, bOut);
                File fsave = new File(Environment.getExternalStorageDirectory() + "/LogophoneContacts/" + Arrays.toString(phone_number).replaceAll("[^0-9]+", "") + str[0] + str[1] + ".jpg");
                fsave.createNewFile();
                FileOutputStream foStream = new FileOutputStream(fsave);
                foStream.write(bOut.toByteArray());
                foStream.close();

                if(str[2].equals("1"))
                    setContactPhoto(bOut.toByteArray(), currentID);

                bOut.flush();
                bOut.close();
                result = true;
            } catch (IOException e) {
                Log.w("com.logophone.save", "Save exception! Debug to more info!");
//                e.printStackTrace();
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
                    filename[0] = "flag/40.png";
                    addLayoutToCanvas(filename[0], bCanvas, 0, 0, false);
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
                ibLeftB.setVisibility(View.VISIBLE);
                ibRightB.setVisibility(View.VISIBLE);
                if(!imgBackground.isShown()) {
                    imgBackground.setImageBitmap(bmapBackground);
                } else if(!imgBackground2.isShown()) {
                    imgBackground2.setImageBitmap(bmapBackground);
                }
                vFlip.showNext();

            }
        }
    }

    private class CreateImageFromContact extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected void onPostExecute(Boolean doCreate) {
            super.onPostExecute(doCreate);
            mProgressDialog.dismiss();
            if(doCreate){
                if(phone_number.length != 0){
                    et1.setText(String.valueOf(phone_number[0]));
                    et1.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et1.setFocusable(false);
                    et2.setText(String.valueOf(phone_number[1]));
                    et2.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et2.setFocusable(false);
                    et3.setText(String.valueOf(phone_number[2]));
                    et3.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et3.setFocusable(false);
                    et4.setText(String.valueOf(phone_number[3] == 10 ? 0 : phone_number[3]));
                    et4.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et4.setFocusable(false);
                    et5.setText(String.valueOf(phone_number[4]));
                    et5.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et5.setFocusable(false);
                    et6.setText(String.valueOf(phone_number[5]));
                    et6.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et6.setFocusable(false);
                    et7.setText(String.valueOf(phone_number[6]));
                    et7.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et7.setFocusable(false);
                    et8.setText(String.valueOf(phone_number[7]));
                    et8.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et8.setFocusable(false);
                    et9.setText(String.valueOf(phone_number[8]));
                    et9.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et9.setFocusable(false);
                    et10.setText(String.valueOf(phone_number[9]));
                    et10.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
                    et10.setFocusable(false);

                    if(showtype != 1){
                        ibLeftM.setVisibility(View.VISIBLE);
                        ibRightM.setVisibility(View.VISIBLE);
                    } else {
                        ibLeftM.setVisibility(View.GONE);
                        ibRightM.setVisibility(View.GONE);
                    }
                    tHelloCreator.setVisibility(View.GONE);
                    if(!image.isShown()){
//                        image.setScaleY((float) 0.95);
//                        image.setScaleX((float) 0.95);
                        image.setImageBitmap(bmapOverlay);
                    } else if(!image2.isShown()){
//                        image2.setScaleY((float) 0.95);
//                        image2.setScaleX((float) 0.95);
                        image2.setImageBitmap(bmapOverlay);
                    }
                    vFlipMain.showNext();
                }
            } else {
                Toast.makeText(mContext, "Bad phone number!", Toast.LENGTH_LONG).show();
                DialogManager(D_GET_NUMBER);
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
                        System.out.println(String.valueOf(file).length() > 4 ? String.valueOf(file).substring(1) : String.valueOf(file) + "XXXX.png");
                        addLayoutToCanvas((String.valueOf(file).length() > 4 ? String.valueOf(file).substring(1) : String.valueOf(file)) + "XXXX.png", canvas, 0, 0, false);
                    }
                    break;
                case 2:
                    int indexOfClothes = indexOfIntArray(aSorted, aZipperType[0]);
                    int indexOfUzor = indexOfIntArray(aSorted, aZipperType[1]);
                    System.out.println(indexOfClothes + "\n" + indexOfUzor);
                    for(int i = 0; i < aSorted.length; i++){
                        if(i == indexOfClothes){
                            filename[5] = (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1) : String.valueOf(aSorted[i])) + "XXXX.png";
                            System.out.println(filename[5]);
                            addLayoutToCanvas(filename[5], canvas, 0, 0, false);
                            filename[7] = "uzors/" + (String.valueOf(aSorted[i]).length() > 4 ? String.valueOf(aSorted[i]).substring(1, 4) : String.valueOf(aSorted[i]).substring(0, 3))
                                    + "X" + (String.valueOf(aSorted[indexOfUzor]).length() > 4 ? String.valueOf(aSorted[indexOfUzor]).substring(2, 3) : String.valueOf(aSorted[indexOfUzor]).substring(1, 2))
                                    + "0XX.png";
                            System.out.println(filename[7]);
                            addLayoutToCanvas(filename[7], canvas, colors_array[aSorted[indexOfUzor] % 10], colors_array[9], true);
                        } else if(i == indexOfUzor || (aSorted[i] == aSorted[indexOfUzor] && i == indexOfUzor)){
                            //do nothing
                        } else {
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
                    filename[9] = "glasses/" + (phone_number[3] == 10 ? 0 : phone_number[3]) + "XXXXX" + phone_number[9] + phone_number[8] + ".png";
                    addLayoutToCanvas(filename[5], canvas, 0, 0, false);
                    addLayoutToCanvas(filename[7], canvas, colors_array[aZipperType[1] % 10], colors_array[9], true);
                    addLayoutToCanvas(filename[9], canvas, 0, 0, false);
                    System.out.println(filename[5]);
                    System.out.println(filename[7]);
                    System.out.println(filename[9]);
                    break;
                default:
                    break;
            }
            publishProgress();
            return true;
        }
    }
}


