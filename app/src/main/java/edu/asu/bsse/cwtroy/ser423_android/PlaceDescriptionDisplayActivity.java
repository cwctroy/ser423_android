package edu.asu.bsse.cwtroy.ser423_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
            new PlaceLibrary(this);
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
      // the user selected the action (garbage can) to remove the student
      case R.id.action_remove:
        android.util.Log.d(this.getClass().getSimpleName(),"onOptionsItemSelected -> remove");
        this.removeStudentAlert();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

//  private void prepareAdapter(){
//    colLabels = this.getResources().getStringArray(R.array.col_header_course);
//    colIds = new int[] {R.id.course_prefix, R.id.course_title};
//    Student aStud = students.get(selectedStud);
//
//    // the model
//    // first row is header strings for the columns
//    fillMaps = new ArrayList<HashMap<String,String>>();
//    HashMap<String,String> titles = new HashMap<>();
//    titles.put("Prefix","Prefix");
//    titles.put("Title","Title");
//    fillMaps.add(titles);
//    // now add the data for the remaining rows
//    Vector<Course> takes = this.sortTakes(aStud.takes);
//    for (int i = 0; i < takes.size(); i++) {
//      Course aCourse = takes.get(i);
//      HashMap<String,String> map = new HashMap<>();
//      Log.d(this.getClass().getSimpleName(),"mapping: "+aCourse.prefix+" "+aCourse.title);
//      map.put("Prefix", aCourse.prefix);
//      map.put("Title", aCourse.title);
//      fillMaps.add(map);
//    }
//  }

  //listview.onitemclicklistener method
  // log the selection when its not the header row.
//  @Override
//  public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//    Student aStud = students.get(selectedStud);
//    if (position > 0 && position < aStud.takes.size()+1) {
//      String prefix = aStud.takes.get(position - 1).prefix;
//      String title = aStud.takes.get(position - 1).title;
//      android.util.Log.d(this.getClass().getSimpleName(), "in method onItemClick. selected: " +
//              prefix + " " + title);
//    }
//  }

  // AdapterView.OnItemSelectedListener method. Called when spinner selection Changes
//  @Override
//  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//    this.selectedCourse = courseSpinner.getSelectedItem().toString();
//    android.util.Log.d(this.getClass().getSimpleName(),"Spinner item "+
//            courseSpinner.getSelectedItem().toString() + " selected.");
//  }

  // AdapterView.OnItemSelectedListener method. Called when spinner selection Changes
//  @Override
//  public void onNothingSelected(AdapterView<?> parent) {
//    android.util.Log.d(this.getClass().getSimpleName(),"In onNothingSelected: No item selected");
//
//  }

  // returns -1 if the course prefix is not found in takes. Otherwise, the index into the vector
  // where it is found.
//  private int findPrefix(Vector<Course> takes, String prefix) {
//    int ret = -1;
//    for(int i=0; i<takes.size(); i++){
//      Course aCrs = takes.get(i);
//      if (aCrs.prefix.equalsIgnoreCase(prefix)){
//        ret = i;
//      }
//    }
//    return ret;
//  }

  // add the course selected from all courses spinner from the student's takes, if not already present
//  public void addClicked (View v) {
//    // the course is not unknown and then the course isn't already in takes
//    if(!this.selectedCourse.equalsIgnoreCase("unknown") &&
//            this.findPrefix(students.get(this.selectedStud).takes,this.selectedCourse)<0){
//      Course aCourse = new Course(this.selectedCourse,
//              availTitles[Arrays.asList(availPrefixes).indexOf(this.selectedCourse)]);
//      (students.get(this.selectedStud).takes).add(aCourse);
//    }
//    this.prepareAdapter();
//    SimpleAdapter sa = new SimpleAdapter(this, fillMaps, R.layout.course_list_item, colLabels, colIds);
//    courseLV.setAdapter(sa);
//
//  }

  // remove the course selected from all courses spinner from the student's takes, if present
//  public void removeClicked (View v) {
//    int isWhere = this.findPrefix(students.get(this.selectedStud).takes,this.selectedCourse);
//    // the course is not unknown and then the course is in takes
//    if(!this.selectedCourse.equalsIgnoreCase("unknown") && isWhere > -1){
//      students.get(this.selectedStud).takes.remove(isWhere);
//    }
//    this.prepareAdapter();
//    SimpleAdapter sa = new SimpleAdapter(this, fillMaps, R.layout.course_list_item, colLabels, colIds);
//    courseLV.setAdapter(sa);
//  }

  // sort ascending the vector of courses by their prefix
//  private Vector<Course> sortTakes(Vector<Course> takes){
//    Vector<Course> ret = takes;
//    for(int i=0; i<ret.size(); i++){
//      for(int j=i+1; j<ret.size(); j++){
//        if(ret.get(i).prefix.compareTo(ret.get(j).prefix) > 0){
//          Course tmp = ret.remove(j);
//          ret.add(i,tmp);
//        }
//      }
//    }
//    return ret;
//  }

  // show an alert view for the user to confirm removing the selected student
  private void removeStudentAlert() {
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
      // ok, so remove the student and return the modified model to main activity
      collection.remove(this.selectedPd);
      Intent i = new Intent();
      i.putExtra("Places", collection);
      this.setResult(RESULT_OK,i);
      finish();
    }
  }

}
