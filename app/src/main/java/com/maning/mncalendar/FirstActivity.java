package com.maning.mncalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


    }

    public void btn01(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btn02(View view) {
        startActivity(new Intent(this, OtherActivity.class));
    }

}
