package com.tyler.simplesurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static final String EXTRA_FIRST_SCORE = "com.tyler.simplesurvey.first_score";
    public static final String EXTRA_SECOND_SCORE = "com.tyler.simplesurvey.second_score";

    private TextView mFirstScore;
    private TextView mSecondScore;
    private Button mResetButton;
    private Button mContinueButton;

    int firstScore = 0;
    int secondScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Get a reference to the intent that launched this activity
        Intent launchIntent = getIntent();
        //Get the Extra's that we added to the Intent in SimpleSurveyActivity
        firstScore = launchIntent.getIntExtra(SimpleSurveyActivity.EXTRA_FIRST_SCORE, 0);
        secondScore = launchIntent.getIntExtra(SimpleSurveyActivity.EXTRA_SECOND_SCORE, 0);

        //Get references to the widgets and display correct tally scores
        mFirstScore = (TextView) findViewById(R.id.dog_score);
        mSecondScore = (TextView) findViewById(R.id.cat_score);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mContinueButton = (Button) findViewById(R.id.continue_button);

        mFirstScore.setText(String.valueOf(firstScore));
        mSecondScore.setText(String.valueOf(secondScore));

        addButtonListeners();

    }

    public void addButtonListeners() {

        // Reset's the scores to 0
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstScore = 0;
                secondScore = 0;
                //resultIntent will carry data back to SimpleSurveyActivity
                Intent resultIntent = new Intent();
                //Add new values as Extras
                resultIntent.putExtra(EXTRA_FIRST_SCORE, firstScore);
                resultIntent.putExtra(EXTRA_SECOND_SCORE, secondScore);
                //Set the result to be OK, and provide resultIntent
                setResult(RESULT_OK, resultIntent);
                //Close Activity
                finish();

            }
        });

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = getIntent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}

