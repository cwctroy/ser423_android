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

    private String name, description, category, address_title, address_street;
    private int elevation;
    private double lattitude, longitude;

    private String jsonSample =
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

    public PlaceDescription(String name, String description, String category, String address_title, String address_street, int elevation, double lattitude, double longitude)
    {
        this.name  = name;
        this.description = description;
        this.category = category;
        this.address_title = address_title;
        this.address_street = address_street;
        this.elevation = elevation;
        this.lattitude = lattitude;
        this.longitude = longitude;
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

    public String getAddress_title() {
        return address_title;
    }

    public void setAddress_title(String address_title) {
        this.address_title = address_title;
    }

    public String getAddressStreet() {
        return address_street;
    }

    public void setAddress(String[] address) {
        this.address_street = address_street;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
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
        json += "\"address_title\" : \"" + this.address_title + "\",\n";
        json += "\"address_street\" : \"" + this.address_street + "\",\n";
        json += "\"elevation\" : \"" + this.elevation+ "\",\n";
        json += "\"lattitude\" : \"" + this.lattitude+ "\",\n";
        json += "\"longitude\" : \"" + this.longitude+ "\",\n";

        json += "}";
        return json;
    }



}
