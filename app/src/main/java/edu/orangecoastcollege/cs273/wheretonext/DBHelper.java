package edu.orangecoastcollege.cs273.wheretonext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    static final String DATABASE_NAME = "WhereToNext";
    private static final String DATABASE_TABLE = "Colleges";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_POPULATION = "population";
    private static final String FIELD_TUITION = "tuition";
    private static final String FIELD_RATING = "rating";
    private static final String FIELD_IMAGE_NAME = "image_name";
    private static final String[] FIELD_ARRAY = {KEY_FIELD_ID, FIELD_NAME, FIELD_POPULATION, FIELD_RATING
    ,FIELD_IMAGE_NAME};


    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){

        // TODO:  Write code to create the database
        //CREATE TABLE whereToNext
        //(_id INTEGER PRIMARY KEY, name TEXT, population Integer, tuition REAL, rating REAL, image_name TEXT)
        String table = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + "INTEGER PRIMARY KEY,"
                + FIELD_NAME + "TEXT,"
                + FIELD_POPULATION + "INTEGER, "
                + FIELD_TUITION + "REAL, "
                + FIELD_RATING + "REAL, "
                + FIELD_IMAGE_NAME + "TEXT" + ")";
        database.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {

        // TODO:  Write code to upgrade the database

    }

    //********** DATABASE OPERATIONS:  ADD, GETALL

    public void addCollege(College college) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME, college.getName());
        values.put(FIELD_POPULATION, college.getPopulation());

        long id = db.insert(DATABASE_TABLE,null,values);
        college.setId(id);
        db.close();

        // TODO:  Write code to add a College to the database
        // TODO:  Return the id assigned by the database
    }

    public ArrayList<College> getAllColleges() {
        ArrayList<College> collegeList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TABLE,FIELD_ARRAY,null,null,null,null,null);

//        if (cursor.isFirst())
//            do {
//                College college = new College(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)
//                , cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5));
//                collegeList.add(college);
//            }while(cursor.moveToNext());

        while (cursor.isAfterLast())
        {
            College newCollege =  new College(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)
                , cursor.getDouble(3),cursor.getDouble(4),cursor.getString(5));

            collegeList.add(newCollege);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();

        // TODO:  Write the code to get all the colleges from the database.

        return collegeList;
    }







}
