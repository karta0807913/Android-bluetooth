package com.example.mikuhatsune.arduino_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by MikuHatsune on 2016/2/19.
 */
public class main extends Activity{

    Bluetooth bluetooth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        initialization();
    }

    private void initialization(){
        bluetooth = new Bluetooth(this, "ArduinoName");
        Button upButton     = (Button)findViewById(R.id.upButton);
        Button downButton   = (Button)findViewById(R.id.downButton);
        Button leftButton   = (Button)findViewById(R.id.leftButton);
        Button rightButton  = (Button)findViewById(R.id.rightButton);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
