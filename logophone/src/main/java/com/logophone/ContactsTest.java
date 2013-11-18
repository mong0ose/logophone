package com.logophone;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by mongOose on 13.11.13.
 */
public class ContactsTest extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_number);
        final Context mContext = this;
        final ArrayAdapter contacts_array;

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        ArrayList<String> mArrayList = new ArrayList<String>();
        if (cur.getCount() > 0) {
            for(cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                String name = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                mArrayList.add(name);
            }
            cur.close();
        }
        contacts_array = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, mArrayList);

        TextView txt = (TextView)findViewById(R.id.phone_number);
        Button bGet = (Button)findViewById(R.id.btnGetNumberFromContacts);

        bGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.list_contacts);
                ListView listContacts = (ListView)dialog.findViewById(R.id.listView);
                listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Cursor curs = mContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + "=\"" + adapterView.getItemAtPosition(i) + "\"", null, null);
                        if(curs.getCount() > 0){
                            curs.moveToFirst();
                            if (Integer.parseInt(curs.getString(curs.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                                //Query phone here.  Covered next
    //                            while (curs.moveToNext()){
                                for(curs.moveToFirst(); !curs.isAfterLast(); curs.moveToNext()) {
                                    System.out.println(adapterView.getItemAtPosition(i));
                                    String number = curs.getString(curs.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    System.out.println(number);
                                }
                            }
                            curs.close();
                        }
                    }
                });
                listContacts.setAdapter(contacts_array);
                dialog.show();
            }
        });

    }
}
