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
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.Hashtable;

public class PlaceLibrary extends AppCompatActivity implements Serializable {

  private Hashtable<String, PlaceDescription> list;

  public PlaceLibrary() {
    list = new Hashtable<>();
  }

  public void log(String message) {
    android.util.Log.d(this.getClass().getSimpleName(), "manual logging: " + message);
  }


  public void add(PlaceDescription pd) {
    log("adding placeDescription: " + ((pd == null) ? "unknown" : pd.getName()));
    try {
      list.put(pd.getName(), pd);
    } catch (Exception ex) {
    }
  }

  public void remove(String pName) {
    log("removing placeDescription: " + pName);
    list.remove(pName);
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

  public void clear() {
    log("Emptying collection");
    list.clear();
  }
}
