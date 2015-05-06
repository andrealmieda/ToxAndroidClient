package com.example.myapp;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Toolbar;


/**
 * Created by andre on 5/6/15.
 */
public class SecondActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondativicty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarView);
        this.setActionBar(toolbar);
        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        toolbar.setBackgroundColor(Color.BLUE);

    }


    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
