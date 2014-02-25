package com.logophone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by mongoose on 25.02.14.
 */
public class InfoListAdapter extends BaseAdapter {
    private Context context;
    private String[] data;
    private int singleItemDigit;
    private static LayoutInflater inflater = null;

    public InfoListAdapter(Context context, String[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public InfoListAdapter(Context context, String data, int digit) {
        // TODO Auto-generated constructor stub
        String[] temp = new String[]{data};
        this.context = context;
        this.data = temp;
        this.singleItemDigit = digit;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.information_row, null);
        TextView tNum, tDecode;
        tNum = (TextView) vi.findViewById(R.id.txtNumElemRow);
        tDecode = (TextView) vi.findViewById(R.id.txtNumElemDisclaimer);

        if(data.length == 1)
            tNum.setText(String.valueOf(singleItemDigit));
        else
            tNum.setText(position < 10 ? String.valueOf(position + 1) : "More then 10");
        tDecode.setText(data[position]);
        return vi;
    }
}
