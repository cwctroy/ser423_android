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
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener, DialogInterface.OnClickListener {

  private EditText nameBox, descriptionBox;
  private ListView pdLV;
  private PlaceLibrary collection;
  private String[] pdNames;

  private String[] colLabels;
  private int[] colIds;
  private List<HashMap<String,String>> fillMaps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pdLV = findViewById(R.id.pd_list_view);

    Intent intent = getIntent();
    collection = intent.getSerializableExtra("PlaceDescriptions")!=null ? (PlaceLibrary)intent.getSerializableExtra("PlaceDescriptions") :
            new PlaceLibrary(this);
    pdNames = collection.getNames();

    this.prepareAdapter();
    SimpleAdapter sa = new SimpleAdapter(this, fillMaps, R.layout.pd_list_item, colLabels, colIds);
    pdLV.setAdapter(sa);
    pdLV.setOnItemClickListener(this);
  }

  private void prepareAdapter() {
    colLabels = this.getResources().getStringArray(R.array.col_header);
    colIds = new int[] {R.id.pd_nameTV, R.id.pd_descriptionTV};
    this.pdNames = collection.getNames();
    Arrays.sort(this.pdNames);
    fillMaps = new ArrayList<>();
    HashMap<String,String> titles = new HashMap<>();
    // first row contains column headers
    titles.put("Name","Name");
    titles.put("Description","Description");
    fillMaps.add(titles);
    // fill in the remaining rows with name and description
    for (String pdName : pdNames) {
      HashMap<String, String> map = new HashMap<>();
      map.put("Name", pdName);
      map.put("Description", collection.get(pdName).getDescription());
      Log.w(this.getClass().getSimpleName(), pdName + " has  name " +
              collection.get(pdName).getName());
      Log.w(this.getClass().getSimpleName(), pdName + " has description " +
              collection.get(pdName).getDescription());
      fillMaps.add(map);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_activity_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    String[] pdNames = collection.getNames();
    Arrays.sort(pdNames);
    if(position > 0 && position <= pdNames.length) {
      android.util.Log.d(this.getClass().getSimpleName(), "in method onItemClick. selected: " + pdNames[position-1]);
      Intent displayPd = new Intent(this, PlaceDescriptionDisplayActivity.class);
      displayPd.putExtra("PlaceDescriptions", collection);
      displayPd.putExtra("selected", pdNames[position-1]);
      this.startActivityForResult(displayPd, 1);
    }
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    collection = (data != null && data.getSerializableExtra("Places") != null) ?
            (PlaceLibrary) data.getSerializableExtra("Places") : collection;
    this.prepareAdapter();
    SimpleAdapter sa = new SimpleAdapter(this, fillMaps, R.layout.pd_list_item, colLabels, colIds);
    pdLV.setAdapter(sa);
    pdLV.setOnItemClickListener(this);
  }

  private void newPlaceDescripitonAlert() {
    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    dialog.setTitle("PlaceDescription name");
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);

    this.nameBox = new EditText(this);
    nameBox.setHint("Name");
    layout.addView(nameBox);

    this.nameBox = new EditText(this);
    nameBox.setHint("Name");
    nameBox.setInputType(InputType.TYPE_CLASS_NUMBER);
    layout.addView(nameBox);
    this.descriptionBox = new EditText(this);
    descriptionBox.setHint("Description");
    nameBox.setInputType(InputType.TYPE_CLASS_NUMBER);
    layout.addView(descriptionBox);
    dialog.setView(layout);
    dialog.setNegativeButton("Cancel", this);
    dialog.setPositiveButton("Add", this);
    dialog.show();
  }

  @Override
  public void onClick(DialogInterface dialog, int whichButton) {
    android.util.Log.d(this.getClass().getSimpleName(),"onClick positive button? "+
            (whichButton==DialogInterface.BUTTON_POSITIVE));
    if(whichButton == DialogInterface.BUTTON_POSITIVE) {

      String nameInput = nameBox.getText().toString().equals("") ? "blank" : nameBox.getText().toString();
      String descriptionInput = descriptionBox.getText().toString().equals("") ? "blank" : descriptionBox.getText().toString();
      PlaceDescription pd = new PlaceDescription();
      pd.setName(nameInput);
      pd.setDescription(descriptionInput);
      collection.add(new PlaceDescription());
      prepareAdapter();
      SimpleAdapter sa = new SimpleAdapter(this, fillMaps, R.layout.pd_display, colLabels, colIds);
      pdLV.setAdapter(sa);
    }
  }

}