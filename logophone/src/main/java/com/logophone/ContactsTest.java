package com.logophone;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mongOose on 13.11.13.
 */
public class ContactsTest extends Activity {
    private static final int DIALOG_CONTACTS = 0;
    private static final int DIALOG_PHONES = 1;
    private Context mContext = this;
    private ArrayAdapter contacts_array;
    private ArrayAdapter phones_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_number);

        TextView txt = (TextView)findViewById(R.id.phone_number);
        Button bGet = (Button)findViewById(R.id.btnGetNumberFromContactsPN);
        Button bCreateImg = (Button)findViewById(R.id.btnCreateImagePN);
        Button bCreateByNumber = (Button)findViewById(R.id.btnCreateByNumberPN);

        bGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);
                ArrayList<String> mArrayList = new ArrayList<String>();
                try {
                    if (cur != null && cur.getCount() > 0) {
                        for(cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                            String name = cur.getString(
                                    cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER))) > 0) {
                                mArrayList.add(name);
                            }
                        }
                        contacts_array = new ArrayAdapter(mContext, android.R.layout.simple_list_item_single_choice, mArrayList);
                        DialogManager(DIALOG_CONTACTS);
                    }
                }finally {
                    cur.close();
                }
            }
        });
        bCreateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Constructor.class));
            }
        });

    }

    private void DialogManager(int D_ID){
        final Dialog dialog = new Dialog(mContext);

        switch (D_ID){
            case DIALOG_CONTACTS:
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.list_contacts);
                Button back_contacts = (Button)dialog.findViewById(R.id.btnBackListContacts);
                back_contacts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ListView listContacts = (ListView)dialog.findViewById(R.id.listViewContacts);

                listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ArrayList<String> phones = new ArrayList<String>();
                        Cursor curs = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=\"" + adapterView.getItemAtPosition(i) + "\"", null, null);
                        try{
                            if(curs != null && curs.getCount() > 0){
                                curs.moveToFirst();
                                if (Integer.parseInt(curs.getString(curs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER))) > 0) {
                                    do{
                                        String number = curs.getString(curs.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                        System.out.println(number);
                                        phones.add(number);
                                    }while(curs.moveToNext());
                                    phones_array = new ArrayAdapter(mContext, android.R.layout.simple_list_item_single_choice, phones);
                                    DialogManager(DIALOG_PHONES);
                                }
                            }
                        }finally {
                            curs.close();
                        }
                    }
                });
                listContacts.setAdapter(contacts_array);
                dialog.show();
                break;
            case DIALOG_PHONES:
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.list_phones);
                Button back_phones = (Button)dialog.findViewById(R.id.btnBackListPhones);
                back_phones.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ListView listPhones = (ListView)dialog.findViewById(R.id.listViewPhones);

                listPhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });
                listPhones.setAdapter(phones_array);
                dialog.show();
                break;
            default:
                break;
        }
    }
}
