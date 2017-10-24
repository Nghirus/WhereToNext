package edu.orangecoastcollege.cs273.wheretonext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.List;

public class CollegeListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<College> collegesList;
    private CollegeListAdapter collegesListAdapter;
    private ListView collegesListView;
    private Button mButton;
    private EditText mNameEditText;
    private EditText mPopulationEditText;
    private EditText mTuitionEditText;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);

        collegesListView = (ListView) findViewById(R.id.collegeListView);
        mButton = (Button) findViewById(R.id.addCollegeButton);
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mPopulationEditText = (EditText) findViewById(R.id.populationEditText);
        mTuitionEditText = (EditText) findViewById(R.id.tuitionEditText);
        mRatingBar = (RatingBar) findViewById(R.id.collegeRatingBar);


        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        // TODO: Comment this section out once the colleges below have been added to the database,
        // TODO: so they are not added multiple times (prevent duplicate entries)
        db.addCollege(new College("UC Berkeley", 42082, 14068, 4.7, "ucb.png"));
        db.addCollege(new College("UC Irvine", 31551, 15026.47, 4.3, "uci.png"));
        db.addCollege(new College("UC Los Angeles", 43301, 25308, 4.5, "ucla.png"));
        db.addCollege(new College("UC San Diego", 33735, 20212, 4.4, "ucsd.png"));
        db.addCollege(new College("CSU Fullerton", 38948, 6437, 4.5, "csuf.png"));
        db.addCollege(new College("CSU Long Beach", 37430, 6452, 4.4, "csulb.png"));

        // TODO:  Fill the collegesList with all Colleges from the database
        collegesList = db.getAllColleges();
        // TODO:  Connect the list adapter with the list
        Log.i("College list:", collegesList.get(0).getName());
        Log.i("College list:", collegesList.get(0).getImageName());
        Log.i("College list:", collegesList.get(2).getName());


        collegesListAdapter = new CollegeListAdapter(this,R.layout.college_list_item,collegesList);
        collegesListView.setAdapter(collegesListAdapter);
        // TODO:  Set the list view to use the list adapter
    }

    public void viewCollegeDetails(View view) {

        // TODO: Implement the view college details using an Intent
    }

    public void addCollege(View view) {

        // TODO: Implement the code for when the user clicks on the addCollegeButton
        College newCollege = new College();
        newCollege.setName(mNameEditText.getText().toString());
        newCollege.setPopulation(Integer.parseInt(mPopulationEditText.getText().toString()));
        newCollege.setTuition(Double.parseDouble(mTuitionEditText.getText().toString()));
        newCollege.setRating((double) mRatingBar.getRating());
        newCollege.setImageName("college.png");

        db.addCollege(newCollege);
        collegesList.add(newCollege);
        collegesListAdapter.notifyDataSetChanged();

        mNameEditText.setText("");
        mTuitionEditText.setText("");
        mPopulationEditText.setText("");
        mRatingBar.setRating(0.0f);


    }

}
