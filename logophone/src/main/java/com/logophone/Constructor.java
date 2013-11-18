package com.logophone;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mongOose on 11.11.13.
 */
public class Constructor extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_show);

//        final View v = new ImageView(getBaseContext());
        final ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

        Display disp = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point p = new Point();
        disp.getSize(p);
        System.out.println("Screen resolution: " + p.x + "x" + p.y);
        Bitmap bMap, bmapOverlay;
        bmapOverlay = Bitmap.createBitmap(p.x, p.y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmapOverlay);
        //setting image resource
//------
        image1 = (ImageView)findViewById(R.id.image1);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._21);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        image2 = (ImageView)findViewById(R.id.image2);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._22);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        image3 = (ImageView)findViewById(R.id.image3);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._23);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y,Color.WHITE, Color.RED), new Matrix(), null);
//------
        image4 = (ImageView)findViewById(R.id.image4);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._24);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        image5 = (ImageView)findViewById(R.id.image5);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._25);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        image6 = (ImageView)findViewById(R.id.image6);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._26);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        image7 = (ImageView)findViewById(R.id.image7);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._27);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);
//------
        image8 = (ImageView)findViewById(R.id.image8);
        bMap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable._28);
        canvas.drawBitmap(resizeBitmap(bMap, p.x, p.y, 0, 0), new Matrix(), null);

        image8.setImageBitmap(bmapOverlay);
    }

    private Bitmap resizeBitmap(Bitmap bMap, int w, int h, int searchColor, int replaceColor){
        Bitmap rbMap = Bitmap.createScaledBitmap(bMap, w, h, false);

//        bMix = Bitmap.createScaledBitmap(bMap, w, h, false);
//        Canvas can = new Canvas(bMix);

        bMap.recycle();
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
                }
            }
        }
        return rbMap;
    }

    private void FloodFill(Bitmap bmp, Point pt, int targetColor, int replacementColor)
    {
        Queue<Point> q = new LinkedList<Point>();
        q.add(pt);
        while (q.size() > 0) {
            Point n = q.poll();
            if (bmp.getPixel(n.x, n.y) != targetColor)
                continue;

            Point w = n, e = new Point(n.x + 1, n.y);
            while ((w.x > 0) && (bmp.getPixel(w.x, w.y) == targetColor)) {
                bmp.setPixel(w.x, w.y, replacementColor);
                if ((w.y > 0) && (bmp.getPixel(w.x, w.y - 1) == targetColor))
                    q.add(new Point(w.x, w.y - 1));
                if ((w.y < bmp.getHeight() - 1)
                        && (bmp.getPixel(w.x, w.y + 1) == targetColor))
                    q.add(new Point(w.x, w.y + 1));
                w.x--;
            }
            while ((e.x < bmp.getWidth() - 1)
                    && (bmp.getPixel(e.x, e.y) == targetColor)) {
                bmp.setPixel(e.x, e.y, replacementColor);

                if ((e.y > 0) && (bmp.getPixel(e.x, e.y - 1) == targetColor))
                    q.add(new Point(e.x, e.y - 1));
                if ((e.y < bmp.getHeight() - 1)
                        && (bmp.getPixel(e.x, e.y + 1) == targetColor))
                    q.add(new Point(e.x, e.y + 1));
                e.x++;
            }
        }
    }
}
