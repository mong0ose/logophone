package com.logophone;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TableLayoutFixed extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        String[][] doublearray = new String[][]{{"Character", "Closes", "Color", "Symbol", "Draw", "Glasses"},
                {"Duck", "Necktie", "White", "Circle", "Circles", "Circles"},
                {"Mouse", "Pants", "Red", "Vertical rectangle", "Vertical stripes", "Vertical rectangle"},
                {"Cat", "Undershirt", "Orange", "Horizontal rectangle", "Horizontal stripes", "Horizontal rectangle"},
                {"Dog", "Shorts", "Yellow", "Triangle", "Triangles", "Triangles"},
                {"Gorilla", "T-shirt", "Green", "Square", "Squares", "Squares"},
                {"Bull", "Trousers", "Sky-blue", "Five-point star", "Five-point stars", "Five-point stars"},
                {"Lion", "Shirt", "Blue", "Hexahedron", "Hexahedrones", "Hexahedrones"},
                {"Crocodile", "Jacket", "Purple", "Parallelepiped", "Parallelepiped's", "Parallelepiped's"},
                {"Bear", "Coat", "Brown", "Octahedron", "Octahedron's", "Octahedron's"},
                {"Behemoth", "Topcoat", "Black", "Rhombus", "Rhombuses", "Rhombuses"}};
        int[] scrollableColumnWidths = new int[]{20, 25, 30, 35, 30};
        int fixedRowHeight = 70;

        TableRow row = new TableRow(this);
        //header (fixed horizontally)
        TableLayout fixedColumn = (TableLayout) findViewById(R.id.fixed_column);
        //rest of the table (within a scroll view)
        TableLayout scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);
        HorizontalScrollView scrollView = (HorizontalScrollView)findViewById(R.id.hScroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        for(int i = 0; i < 11; i++) {
            TextView fixedView;
            if(i == 0)
                fixedView = makeTableRowWithText("Number", scrollableColumnWidths[0], fixedRowHeight);
            else
                fixedView = makeTableRowWithText(String.valueOf(i-1), scrollableColumnWidths[0], fixedRowHeight);
            fixedView.setBackgroundColor(Color.CYAN);
            fixedColumn.addView(fixedView);
            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.WHITE);
            row.addView(makeTableRowWithText(doublearray[i][0], scrollableColumnWidths[1], fixedRowHeight));
            row.addView(makeTableRowWithText(doublearray[i][1], scrollableColumnWidths[2], fixedRowHeight));
            row.addView(makeTableRowWithText(doublearray[i][2], scrollableColumnWidths[3], fixedRowHeight));
            row.addView(makeTableRowWithText(doublearray[i][3], scrollableColumnWidths[3], fixedRowHeight));
            row.addView(makeTableRowWithText(doublearray[i][4], scrollableColumnWidths[3], fixedRowHeight));
            row.addView(makeTableRowWithText(doublearray[i][5], scrollableColumnWidths[3], fixedRowHeight));
            scrollablePart.addView(row);
        }

    }

    //util method
    private TextView recyclableTextView;

    public TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setGravity(Gravity.CENTER);
        recyclableTextView.setTextColor(Color.BLACK);
        recyclableTextView.setTextSize(15);
        recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        recyclableTextView.setHeight(fixedHeightInPixels);
        return recyclableTextView;
    }

}