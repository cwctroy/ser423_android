package com.example.ser423_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView myTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTV = (TextView) findViewById(R.id.my_text_view);
    }

    public void buttonClicked(View v) {
       android.util.Log.d(this.getClass().getSimpleName(), "button clicked");
       myTV.setText(myTV.getText() + "ouch, ");
    }
}