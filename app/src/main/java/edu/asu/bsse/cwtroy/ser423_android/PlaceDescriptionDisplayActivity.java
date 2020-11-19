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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlaceDescriptionDisplayActivity extends AppCompatActivity implements
        DialogInterface.OnClickListener {

  private PlaceLibrary collection;
  private TextView pd_nameTV, pd_descriptionTV, pd_categoryTV, pd_addressTitleTV, pd_addressStreetTV;
  private TextView pd_elevationTV, pd_latitudeTV, pd_longitudeTV;
  private String selectedPd;

  private String[] colLabels;
  private int[] colIds;
  private List<HashMap<String,String>> fillMaps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.pd_display);
    pd_nameTV = findViewById(R.id.name_detail);
    pd_descriptionTV = findViewById(R.id.description_detail);
    pd_categoryTV = findViewById(R.id.category_detail);
    pd_addressTitleTV = findViewById(R.id.address_title_detail);
    pd_addressStreetTV = findViewById(R.id.address_street_detail);
    pd_elevationTV = findViewById(R.id.elevation_detail);
    pd_latitudeTV = findViewById(R.id.latitude_detail);
    pd_longitudeTV = findViewById(R.id.longitude_detail);


    Intent intent = getIntent();
    collection = intent.getSerializableExtra("PlaceDescriptions")!=null ? (PlaceLibrary)intent.getSerializableExtra("PlaceDescriptions") :
            new PlaceLibrary();
    selectedPd = intent.getStringExtra("selected")!=null ? intent.getStringExtra("selected") : "unknown";
    PlaceDescription pd = collection.get(selectedPd);
    pd_nameTV.setText(pd.getName());
    pd_descriptionTV.setText(pd.getDescription());
    pd_categoryTV.setText(pd.getCategory());
    pd_addressTitleTV.setText(pd.getAddressTitle());
    pd_addressStreetTV.setText(pd.getAddressStreet());
    pd_elevationTV.setText("" + pd.getElevation());
    pd_latitudeTV.setText(Double.toString(pd.getLatitude()));
    pd_longitudeTV.setText(Double.toString(pd.getLongitude()));

    // set up a back button to return to the view main activity / view
    try {
      Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
    }catch(Exception ex){
      android.util.Log.d(this.getClass().getSimpleName(),"exception action bar: "+ex.getLocalizedMessage());
    }
    setTitle("Place Description");
  }

  // create the menu items for this activity, placed in the action bar.
  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.pd_display_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  /*
   * Implement onOptionsItemSelected(MenuItem item){} to handle clicks of buttons that are
   * in the action bar.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected() id: "+item.getItemId()
            +" title "+item.getTitle());
    switch (item.getItemId()) {
      // the user selected the up/home button (left arrow at left of action bar)
      case android.R.id.home:
        android.util.Log.d(this.getClass().getSimpleName(),"onOptionsItemSelected -> home");
        Intent i = new Intent();
        i.putExtra("Places", collection);
        this.setResult(RESULT_OK,i);
        finish();
        return true;
      // the user selected the action (garbage can) to remove the place description
      case R.id.action_remove:
        android.util.Log.d(this.getClass().getSimpleName(),"onOptionsItemSelected -> remove");
        this.removePlaceDescriptionAlert();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  // show an alert view for the user to confirm removing the selected place description
  private void removePlaceDescriptionAlert() {
    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    dialog.setTitle("Remove place description "+this.selectedPd+"?");
    dialog.setNegativeButton("Cancel", this);
    dialog.setPositiveButton("Remove", this);
    dialog.show();
  }

  // DialogInterface.onClickListener method. Gets called when negative or positive button is clicked
  // in the Alert Dialog created by the newStudentAlert method.
  @Override
  public void onClick(DialogInterface dialog, int whichButton) {
    android.util.Log.d(this.getClass().getSimpleName(),"onClick positive button? "+
            (whichButton==DialogInterface.BUTTON_POSITIVE));
    if(whichButton == DialogInterface.BUTTON_POSITIVE) {
      log("Deleting place: " + this.selectedPd);
      PlaceDescriptionDB db = new PlaceDescriptionDB(this);
      SQLiteDatabase placeDB = db.openDB();
      String delete = "DELETE FROM places where places.name = \"" + this.selectedPd + "\";";
      placeDB.execSQL(delete);
      db.close();
      collection.remove(this.selectedPd);

      Intent i = new Intent();
      i.putExtra("Places", collection);
      this.setResult(RESULT_OK,i);
      finish();
    }
  }

  public void log(String message) {
    android.util.Log.d(this.getClass().getSimpleName(), "manual logging: " + message);
  }
}
