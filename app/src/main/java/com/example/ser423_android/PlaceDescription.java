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

import org.json.JSONObject;

public class PlaceDescription {

    private String name, description, category, addressTitle, addressStreet;
    private int elevation;
    private double latitude, longitude;


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

    public PlaceDescription(String jsonStr) {
        try{
            JSONObject jo = new JSONObject(jsonStr);
            this.name = jo.getString("name");
            this.description = jo.getString("description");
            this.category = jo.getString("category");
            this.addressTitle = jo.getString("address-title");
            this.addressStreet = jo.getString("address-street");
            this.elevation = jo.getInt("elevation");
            this.latitude = jo.getDouble("latitude");
            this.longitude = jo.getDouble("longitude");
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"error converting to/from json");
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

    public String toJsonString(){
        String ret = "";
        try{
            JSONObject jo = new JSONObject();
            jo.put("name", this.name);
            jo.put("description", this.description);
            jo.put("category", this.category);
            jo.put("address-title", this.addressTitle);
            jo.put("address-street", addressStreet);
            jo.put("elevation", this.elevation);
            jo.put("latitude", this.latitude);
            jo.put("longitude", this.longitude);
            ret = jo.toString();
        }catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),
                    "error converting to/from json");
        }
        return ret;
    }

    public String toString() {
	String ret = "Name : " + this.name + 
		"\nDescription: " + this.description + 
		"\nCategory: " + this.category +
		"\nAddress line 1: " + this.addressTitle + 
		"\nAddress line 2: " + this.addressStreet +
		"\nElevation: " + this.elevation +
		"\nLatitude: " + this.latitude +
		"\nLongitude: " + this.longitude;
	return ret;
    }

}
