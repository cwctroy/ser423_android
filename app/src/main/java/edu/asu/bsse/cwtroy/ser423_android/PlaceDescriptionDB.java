package edu.asu.bsse.cwtroy.ser423_android;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PlaceDescriptionDB extends SQLiteOpenHelper {
    private static final boolean debugon = true;
    private static final int DATABASE_VERSION = 3;
    private static final String dbName = "placesdb";
    private String dbPath;
    private SQLiteDatabase placesDB;
    private final Context context;

    public PlaceDescriptionDB(Context context){
      super(context,dbName, null, DATABASE_VERSION);
      this.context = context;
      log("getting dbPath from context");
      // place the database in the files directory. Could also place it in the databases directory
      // with dbPath = context.getDatabasePath("dbName"+".db").getPath();
      dbPath = context.getFilesDir().getPath()+"/";
      android.util.Log.d(this.getClass().getSimpleName(),"db path is: "+
              context.getDatabasePath(dbName));
      android.util.Log.d(this.getClass().getSimpleName(),"dbpath: "+dbPath);
    }

//    public void createDB() {
//      this.getReadableDatabase();
//      try {
//        copyDB();
//      } catch (IOException e) {
//        android.util.Log.w(this.getClass().getSimpleName(),
//                "createDB Error copying database " + e.getMessage());
//      }
//    }

    /**
     * Does the database exist and has it been initialized? This method determines whether
     * the database needs to be copied to the data/data/pkgName/files directory by
     * checking whether the file exists. If it does it checks to see whether the db is
     * uninitialized or whether it has the course table.
     * @return false if the database file needs to be copied from the assets directory, true
     * otherwise.
     */
    private boolean checkDB(){    //does the database exist and is it initialized?
      SQLiteDatabase checkDB = null;
      boolean placeTabExists = false;
      log("Checking if database exists");
      try{
        String path = dbPath + dbName + ".db";
        debug("PlacesDB --> checkDB: path to db is", path);
        File aFile = new File(path);
        if(aFile.exists()){
          checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
          if (checkDB!=null) {
            debug("PlacesDB --> checkDB","opened db at: "+checkDB.getPath());
            Cursor tabChk = checkDB.rawQuery("SELECT name FROM sqlite_master where type='table' and name='places';", null);
            if(tabChk == null){
              debug("PlacesDB --> checkDB","check for place table result set is null");
            }else{
              tabChk.moveToNext();
              debug("PlacesDB --> checkDB","check for place table result set is: " +
                      ((tabChk.isAfterLast() ? "empty" : tabChk.getString(0))));
              placeTabExists = !tabChk.isAfterLast();
            }
            if(placeTabExists){
              Cursor c= checkDB.rawQuery("SELECT * FROM places", null);
              c.moveToFirst();
              while(!c.isAfterLast()) {
                String placeName = c.getString(0);
                String placeDescription = c.getString(1);
                debug("Places --> checkDB","Place table has PlaceName: "+
                        placeName+"\tPlace Description: "+placeDescription);
                c.moveToNext();
              }
              placeTabExists = true;
              c.close();
            }
            if (tabChk != null) {
              tabChk.close();
            }
          }
        }
      }catch(SQLiteException e){
        android.util.Log.w("PlacesDB->checkDB",e.getMessage());
      }
      if(checkDB != null){
        checkDB.close();
      }
      return placeTabExists;
    }

    public void copyDB() throws IOException{
      try {
        if(!checkDB()){
          log("db does not already exist, creating");
          // only copy the database if it doesn't already exist in my database directory
          debug("PlacesDB --> copyDB", "checkDB returned false, starting copy");
          InputStream ip =  context.getResources().openRawResource(R.raw.placesdb);
          // make sure the database path exists. if not, create it.
          File aFile = new File(dbPath);
          if(!aFile.exists()){
            aFile.mkdirs();
          }
          String op=  dbPath  +  dbName +".db";
          OutputStream output = new FileOutputStream(op);
          byte[] buffer = new byte[1024];
          int length;
          while ((length = ip.read(buffer))>0){
            output.write(buffer, 0, length);
          }
          output.flush();
          output.close();
          ip.close();
        }
      } catch (IOException e) {
        android.util.Log.w("PlacesDB --> copyDB", "IOException: "+e.getMessage());
      }
    }

    public SQLiteDatabase openDB() throws SQLException {
      String myPath = dbPath + dbName + ".db";
      log("Opening database at path" + myPath) ;
      if(checkDB()) {
        placesDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        debug("PlacesDB --> openDB", "opened db at path: " + placesDB.getPath());
      }else{
        try {
          this.copyDB();
          placesDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }catch(Exception ex) {
          android.util.Log.w(this.getClass().getSimpleName(),"unable to copy and open db: "+ex.getMessage());
        }
      }
      return placesDB;
    }

    @Override
    public synchronized void close() {
      if(placesDB != null)
        placesDB.close();
      super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void debug(String hdr, String msg){
      if(debugon){
        android.util.Log.d(hdr,msg);
      }
    }

  public void log(String message) {
    android.util.Log.d(this.getClass().getSimpleName(), "manual logging: " + message);
  }

}

