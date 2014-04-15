package com.logophone;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.logophone.adapters.SampleTableAdapter;

public class TableLayoutFixed extends Activity {
    private static final int[] colors_array = {
            Color.rgb(1, 1, 1),                 // BLACK
            Color.rgb(155, 155, 155),           // WHITE
            Color.rgb(255, 1, 1),               // RED
            Color.rgb(255, 153, 51),            // ORANGE
            Color.rgb(225, 225, 35),            // YELLOW
            Color.rgb(5, 175, 5),               // GREEN
            Color.rgb(140, 190, 252),           // SKY-BLUE
            Color.rgb(55, 55, 225),             // BLUE
            Color.rgb(182, 29, 142),            // PURPLE
            Color.rgb(186, 114, 41),            // BROWN
            Color.rgb(1, 1, 1)                  // BLACK
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
    }

    private static final String[][] doublearray = new String[][]{{"№", "Character", "Clothing", "Color", "Figure", "Pattern", "Glasses"},
            {"0", "Duck", "Necktie", "White", "Circle", "Circles", "Circles"},
            {"1", "Mouse", "Trunks", "Red", "Vertical rectangle", "Vertical stripes", "Vertical rectangles"},
            {"2", "Cat", "Sleeveless shirt", "Orange", "Horizontal rectangle", "Horizontal stripes", "Horizontal rectangles"},
            {"3", "Dog", "Shorts", "Yellow", "Triangle", "Triangles", "Triangles"},
            {"4", "Gorilla", "T-shirt", "Green", "Square", "Squares", "Squares"},
            {"5", "Bull", "Pants", "Blue", "Five-point star", "Five-point stars", "Five-point stars"},
            {"6", "Leo", "Shirt", "Deep blue", "Hexagon", "Hexagons", "Hexagons"},
            {"7", "Crocodile", "Blazer", "Purple", "Parallelepiped", "Slanting stripes", "Slanting stripes"},
            {"8", "Bear", "Jacket", "Brown", "Octagon", "Flowers", "Flowers"},
            {"9", "Hippo", "Coat", "Black", "Diamond", "Diamonds", "Diamonds"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        TableFixHeaders tableFixHeaders = (TableFixHeaders) findViewById(R.id.table);
        tableFixHeaders.setAdapter(new MyAdapter(this));
    }

    public class MyAdapter extends SampleTableAdapter {

        private final int width;
        private final int height;

        public MyAdapter(Context context) {
            super(context);

            Resources resources = context.getResources();

            width = resources.getDimensionPixelSize(R.dimen.table_width);
            height = resources.getDimensionPixelSize(R.dimen.table_height);
        }

        @Override
        public int getRowCount() {
            return 10;
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public int getWidth(int column) {
            if(column == -1)
                return 100;
            return width;
        }

        @Override
        public int getHeight(int row) {
            return height;
        }

        @Override
        public int getCellColor(int row) {
            return colors_array[row+1];
        }

        @Override
        public String getCellString(int row, int column) {
            return doublearray[row+1][column+1];
        }

        @Override
        public int getLayoutResource(int row, int column) {
            final int layoutResource;
            switch (getItemViewType(row, column)) {
                case 0:
                    layoutResource = R.layout.item_table1_header;
                    break;
                case 1:
                    layoutResource = R.layout.item_table1;
                    break;
                default:
                    throw new RuntimeException("wtf?");
            }
            return layoutResource;
        }

        @Override
        public int getItemViewType(int row, int column) {
            if (row < 0) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }


//    private int[] colors_array = {
//            Color.rgb(1, 1, 1),                 // BLACK
//            Color.rgb(245, 245, 245),           // WHITE
//            Color.rgb(255, 1, 1),               // RED
//            Color.rgb(255, 153, 51),            // ORANGE
//            Color.rgb(255, 255, 1),             // YELLOW
//            Color.rgb(5, 233, 5),               // GREEN
//            Color.rgb(140, 190, 252),           // SKY-BLUE
//            Color.rgb(1, 1, 255),               // BLUE
//            Color.rgb(182, 29, 142),            // PURPLE
//            Color.rgb(186, 114, 41),            // BROWN
//            Color.rgb(1, 1, 1)                  // BLACK
//    };
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.table);
//
//        TableRow.LayoutParams wrapWrapTableRowParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        String[][] doublearray = new String[][]{{"Character", "Clothing", "Color", "Figure", "Pattern", "Glasses"},
//                {"Duck", "Necktie", "White", "Circle", "Circles", "Circles"},
//                {"Mouse", "Trunks", "Red", "Vertical rectangle", "Vertical stripes", "Vertical rectangles"},
//                {"Cat", "Sleeveless shirt", "Orange", "Horizontal rectangle", "Horizontal stripes", "Horizontal rectangles"},
//                {"Dog", "Shorts", "Yellow", "Triangle", "Triangles", "Triangles"},
//                {"Gorilla", "T-shirt", "Green", "Square", "Squares", "Squares"},
//                {"Bull", "Pants", "Blue", "Five-point star", "Five-point stars", "Five-point stars"},
//                {"Leo", "Shirt", "Deep blue", "Hexagon", "Hexagons", "Hexagons"},
//                {"Crocodile", "Blazer", "Purple", "Parallelepiped", "Slanting stripes", "Slanting stripes"},
//                {"Bear", "Jacket", "Brown", "Octagon", "Flowers", "Flowers"},
//                {"Hippo", "Coat", "Black", "Diamond", "Diamonds", "Diamonds"}};
//        int[] scrollableColumnWidths = new int[]{20, 25, 30, 35, 30};
//        int fixedRowHeight = 100;
//
//        TableRow row = new TableRow(this);
//        //header (fixed horizontally)
//        TableLayout fixedColumn = (TableLayout) findViewById(R.id.fixed_column);
//        //rest of the table (within a scroll view)
//        TableLayout scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);
//        HorizontalScrollView scrollView = (HorizontalScrollView)findViewById(R.id.hScroll);
//        scrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int action = event.getAction();
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN:
//                        // Disallow ScrollView to intercept touch events.
//                        v.getParent().requestDisallowInterceptTouchEvent(true);
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        // Allow ScrollView to intercept touch events.
//                        v.getParent().requestDisallowInterceptTouchEvent(false);
//                        break;
//                }
//
//                // Handle ListView touch events.
//                v.onTouchEvent(event);
//                return true;
//            }
//        });
//        for(int i = 0; i < 11; i++) {
//            TextView fixedView;
//            if(i == 0)
//                fixedView = makeTableRowWithText(" № ", scrollableColumnWidths[0], fixedRowHeight, colors_array[10]);
//            else
//                fixedView = makeTableRowWithText(String.valueOf(i-1), scrollableColumnWidths[0], fixedRowHeight, colors_array[i]);
////            fixedView.setBackgroundColor(Color.CYAN);
//            fixedColumn.addView(fixedView);
//            row = new TableRow(this);
//            row.setLayoutParams(wrapWrapTableRowParams);
//
//            row.setGravity(Gravity.CENTER);
//            row.setBackgroundColor(Color.WHITE);
//
//            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
//            row.addView(makeTableRowWithText(doublearray[i][0], scrollableColumnWidths[1], fixedRowHeight, colors_array[i]));
//            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
//            row.addView(makeTableRowWithText(doublearray[i][1], scrollableColumnWidths[2], fixedRowHeight, colors_array[i]));
//            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
//            row.addView(makeTableRowWithText(doublearray[i][2], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
//            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
//            row.addView(makeTableRowWithText(doublearray[i][3], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
//            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
//            row.addView(makeTableRowWithText(doublearray[i][4], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
//            row.addView(makeTableRowWithText(null, 1, fixedRowHeight, 0));
//            row.addView(makeTableRowWithText(doublearray[i][5], scrollableColumnWidths[3], fixedRowHeight, colors_array[i]));
//
////            row.addView(makeEdgeInTable(1, fixedRowHeight));
//            scrollablePart.addView(row);
//        }
//
//    }
//
//    public TextView makeTableRowWithText(String text, int widthInPercentOfScreenWidth, int fixedHeightInPixels, int clr) {
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        TextView recyclableTextView = new TextView(this);
////        recyclableTextView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ));
//        if(text != null){
//            recyclableTextView.setText(text);
//            recyclableTextView.setGravity(Gravity.CENTER);
//            if(clr == colors_array[10])
//                recyclableTextView.setTextColor(Color.WHITE);
//            else
//                recyclableTextView.setTextColor(Color.BLACK);
//            recyclableTextView.setTextSize(20);
//            recyclableTextView.setBackgroundColor(clr);
//            recyclableTextView.setWidth(widthInPercentOfScreenWidth * screenWidth / 100);
//        }else {
//            recyclableTextView.setBackgroundColor(Color.parseColor("#DADADA"));
//            recyclableTextView.setWidth(widthInPercentOfScreenWidth);
//        }
//        recyclableTextView.setHeight(fixedHeightInPixels);
//
////        recyclableTextView.set
//        return recyclableTextView;
//    }

}