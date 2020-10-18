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

    private final String jsonSample =
            "{\n" +
                    "\"name\" : \"ASU-Poly\",\n" +
                    "\"description\" : \"Home of ASU's Software Engineering Programs\",\n" +
                    "\"category\" : \"School\",\n" +
                    "\"address-title\" : \"ASU Software Engineering\",\n" +
                    "\"address-street\" : \"7171 E Sonoran Arroyo Mall\nPeralta Hall 230\nMesa AZ 85212\",\n" +
                    "\"elevation\" : 1384.0,\n" +
                    "\"latitude\" : 33.306388,\n" +
                    "\"longitude\" : -111.679121\n" +
                    "}";

    private TextView name, description, category, addressTitle, addressStreet, elevation, latitude, longitude;
    PlaceDescription PD = new PlaceDescription(jsonSample);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView) findViewById(R.id.name_content);
        description = (TextView) findViewById(R.id.description_content);
        category = (TextView) findViewById(R.id.category_content);
        addressTitle = (TextView) findViewById(R.id.address_title_content);
        addressStreet = (TextView) findViewById(R.id.address_street_content);
        elevation = (TextView) findViewById(R.id.elevation_content);
        latitude = (TextView) findViewById(R.id.latitude_content);
        longitude = (TextView) findViewById(R.id.longitude_content);

    }

    public void buttonClicked(View v) {
       android.util.Log.d(this.getClass().getSimpleName(), "button clicked");
       name.setText(PD.getName());
       description.setText(PD.getDescription());
       category.setText(PD.getCategory());
       addressTitle.setText(PD.getAddressTitle());
       addressStreet.setText(PD.getAddressStreet());
       elevation.setText(String.valueOf(PD.getElevation()));
       latitude.setText(String.valueOf(PD.getLatitude()));
       longitude.setText(String.valueOf(PD.getLongitude()));
    }
}