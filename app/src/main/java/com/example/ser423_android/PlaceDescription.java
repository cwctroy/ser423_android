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

public class PlaceDescription {

    private String name, description, category, addressTitle, addressStreet;
    private int elevation;
    private double latitude, longitude;

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

    public PlaceDescription() {
    }
    
    public PlaceDescription(String name, String description, String category, String addressTitle, String addressStreet, int elevation, double latitude, double longitude)
    {
        this.name  = name;
        this.description = description;
        this.category = category;
        this.addressTitle = addressTitle;
        this.addressStreet = addressStreet;
        this.elevation = elevation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PlaceDescription(String jsonString) {
        String[] values = jsonString.split(",");
        for (String s : values) {
            String[] pair = s.split(":");
            String value = pair[1].substring(1, pair[1].length() - 2);
            if (pair[0].contains("name")) {
                this.setName(value);
            } else if (pair[0].contains("description")) {
                this.setDescription(value);
            } else if (pair[0].contains("category")) {
                this.setCategory(value);
            } else if (pair[0].contains("address-title")) {
                this.setAddressTitle(value);
            } else if (pair[0].contains("address-street")) {
                this.setAddressStreet(value);
            } else if (pair[0].contains("elevation")) {
                this.setElevation(Integer.getInteger(value));
            } else if (pair[0].contains("latitude")) {
                this.setLatitude(Double.parseDouble(value));
            } else if (pair[0].contains("longitude")) {
                this.setLongitude(Double.parseDouble(value));
            } else {
                System.out.println("Malformed json");
                //Custom exception here?
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String address) {
        this.addressStreet = addressStreet;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String toJsonString() {
        String json = "{\n";

        json += "\"name\" : \"" + this.name + "\",\n";
        json += "\"description\" : \"" + this.description + "\",\n";
        json += "\"category\" : \"" + this.category + "\",\n";
        json += "\"address-title\" : \"" + this.addressTitle + "\",\n";
        json += "\"address-street\" : \"" + this.addressStreet + "\",\n";
        json += "\"elevation\" : \"" + this.elevation+ "\",\n";
        json += "\"lattitude\" : \"" + this.latitude + "\",\n";
        json += "\"longitude\" : \"" + this.longitude+ "\",\n";

        json += "}";
        return json;
    }



}
