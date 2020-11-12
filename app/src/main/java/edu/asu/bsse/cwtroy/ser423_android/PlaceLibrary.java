/*
 * Copyright 2020  Cameron Troy,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Cameron Troy, cwtroy@asu.edu
 * Software Engineering, CIDSE, ASU Poly
 *
 * @version November 2020
 */

package edu.asu.bsse.cwtroy.ser423_android;

import android.app.Activity;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;

public class PlaceLibrary implements Serializable {

  private Hashtable<String, PlaceDescription> list;

  public PlaceLibrary(Activity parent) {
    list = new Hashtable<String, PlaceDescription>();
    try {
      this.resetFromJsonFile(parent);
    } catch (Exception e) {
      log("Error resetting place descriptions from json file" + e.getMessage());
    }
  }

  public void log(String message) {
    android.util.Log.d(this.getClass().getSimpleName(), message);
  }

  public boolean resetFromJsonFile(Activity parent) {
    boolean ret = true;
    try {
      list.clear();
      InputStream is = parent.getApplicationContext().getResources().openRawResource(R.raw.locations);
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      // note that the json is in a multiple lines of input so need to read line-by-line
      StringBuffer sb = new StringBuffer();
      while (br.ready()) {
        sb.append(br.readLine());
      }
      String placesJsonStr = sb.toString();
      JSONObject placesJson = new JSONObject(new JSONTokener(placesJsonStr));
      Iterator<String> it = placesJson.keys();
      while (it.hasNext()) {
        String pName = it.next();
        JSONObject place = placesJson.optJSONObject(pName);
        log(pName + " json is: " + place.toString());
        if (place != null) {
          PlaceDescription pd = new PlaceDescription(place.toString());
          list.put(pName, pd);
        }
      }
    } catch (Exception ex) {
      log("Exception reading json file: " + ex.getMessage());
      ret = false;
    }
    return ret;
  }

  public boolean add(PlaceDescription pd) {
    boolean ret = true;
    log("adding placeDescription: " + ((pd == null) ? "unknown" : pd.getName()));
    try {
      list.put(pd.getName(), pd);
    } catch (Exception ex) {
      ret = false;
    }
    return ret;
  }

  public boolean remove(String pName) {
    log("removing placeDescription: " + pName);
    return (list.remove(pName) != null);
  }

  public String[] getNames() {
    String[] ret = {};
    log("getting " + list.size() + " placeDescription names");
    if (list.size() > 0) {
      ret = (String[]) (list.keySet()).toArray(new String[0]);
    }
    return ret;
  }

  public PlaceDescription get(String pName) {
    PlaceDescription ret = new PlaceDescription();
    PlaceDescription pd = list.get(pName);
    if (pd != null) {
      ret = pd;
    }
    return ret;
  }
}
