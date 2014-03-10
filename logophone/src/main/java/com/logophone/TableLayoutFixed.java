package com.logophone;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TableLayoutFixed extends Activity {
    private int[] colors_array = {
            Color.rgb(1, 1, 1),                 // BLACK
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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        String[][] doublearray = new String[][]{{"Character", "Clothing", "Color", "Figure", "Pattern", "Glasses"},
                {"Duck", "Necktie", "White", "Circle", "Circles", "Circles"},
                {"Mouse", "Trunks", "Red", "Vertical rectangle", "Vertical stripes", "Vertical rectangles"},
                {"Cat", "Sleeveless shirt", "Orange", "Horizontal rectangle", "Horizontal stripes", "Horizontal rectangles"},
                {"Dog", "Shorts", "Yellow", "Triangle", "Triangles", "Triangles"},
                {"Gorilla", "T-shirt", "Green", "Square", "Squares", "Squares"},
                {"Bull", "Pants", "Blue", "Five-point star", "Five-point stars", "Five-point stars"},
                {"Leo", "Shirt", "Deep blue", "Hexagon", "Hexagons", "Hexagons"},
                {"Crocodile", "Blazer", "Purple", "Parallelepiped", "Slanting stripes", "Slanting stripes"},
                {"Bear", "Jacket", "Brown", "Octagon", "Flowers", "Flowers"},
                {"Hippo", "Coat", "Black", "Diamond", "Diamonds", "Diamonds"}};
        int[] scrollableColumnWidths = new int[]{20, 25, 30, 35, 30};
        int fixedRowHeight = 100;

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
                fixedView = makeTableRowWithText(" № ", scrollableColumnWidths[0], fixedRowHeight, colors_array[10]);
            else
                fixedView = makeTableRowWithText(String.valueOf(i-1), scrollableColumnWidths[0], fixedRowHeight, colors_array[i]);
//            fixedView.setBackgroundColor(Color.CYAN);
            fixedColumn.addView(fixedView);
            row = new TableRow(this);
            row.setLayoutParams(wrapWrapTableRowParams);

            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.WHITE);

            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
            row.addView(makeTableRowWithText(doublearray[i][0], scrollableColumnWidths[1], fixedRowHeight, colors_array[i]));
            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
            row.addView(makeTableRowWithText(doublearray[i][1], scrollableColumnWidths[2], fixedRowHeight, colors_array[i]));
            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
            row.addView(makeTableRowWithText(doublearray[i][2], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
            row.addView(makeTableRowWithText(doublearray[i][3], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
            row.addView(makeTableRowWithText(doublearray[i][4], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
            row.addView(makeTableRowWithText(doublearray[i][5], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));

//            row.addView(makeEdgeInTable(1, fixedRowHeight));
            scrollablePart.addView(row);
        }

    }

    public TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels, int clr) {
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        TextView recyclableTextView = new TextView(this);
//        recyclableTextView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ));
        if(text != null){
            recyclableTextView.setText(text);
            recyclableTextView.setGravity(Gravity.CENTER);
            if(clr == colors_array[10])
                recyclableTextView.setTextColor(Color.WHITE);
            else
                recyclableTextView.setTextColor(Color.BLACK);
            recyclableTextView.setTextSize(20);
            recyclableTextView.setBackgroundColor(clr);
            recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
        }else {
            recyclableTextView.setBackgroundColor(Color.parseColor("#DADADA"));
            recyclableTextView.setWidth(widthInPercentOfScreenWidth);
        }
        recyclableTextView.setHeight(fixedHeightInPixels);

//        recyclableTextView.set
        return recyclableTextView;
    }

}