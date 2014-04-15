package com.logophone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mongOose on 08.04.14.
 */
public class GameModeChooser extends Activity {

    private Context mContext = this;
    private int[] colors_array = {
            Color.rgb(245, 245, 245),           // WHITE
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_gametype_manu);

        Button bTest, bTraining, bVisualizer, bExit;
        bVisualizer = (Button)findViewById(R.id.btnLearn);
        bVisualizer.getBackground().setColorFilter(colors_array[1], PorterDuff.Mode.MULTIPLY);
        bVisualizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Visualizer.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        bTraining = (Button) findViewById(R.id.btnTraining);
        bTraining.getBackground().setColorFilter(colors_array[3], PorterDuff.Mode.MULTIPLY);
        bTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, TrainingPart.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        bTest = (Button)findViewById(R.id.btnTest);
        bTest.getBackground().setColorFilter(colors_array[4], PorterDuff.Mode.MULTIPLY);
        bTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, TestingGame.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });
        bExit = (Button)findViewById(R.id.btnBackToMainMenu);
        bExit.getBackground().setColorFilter(colors_array[6], PorterDuff.Mode.MULTIPLY);
        bExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_b_in, R.anim.slide_b_out);
            }
        });
    }
}
