/* Copyright 2020
 * Cameron W.C. Troy,
 *
 * This software is the intellectual property of the author, and can not be distributed, used, copied, or
 * reproduced, in whole or in part, for any purpose, commercial or otherwise. The author grants the ASU
 * Software Engineering program the right to copy, execute, and evaluate this work for the purpose of
 * determining performance of the author in coursework, and for Software Engineering program evaluation,
 * so long as this copyright and right-to-use statement is kept in-tact in such use.
 * All other uses are prohibited and reserved to the author
 *
 * @author Cameron Troy cwtroy@asu.edu
 * @version October 2020
 */

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