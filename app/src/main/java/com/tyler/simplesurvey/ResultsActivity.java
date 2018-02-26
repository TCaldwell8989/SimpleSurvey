package com.tyler.simplesurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static final String EXTRA_FIRST_RESULT = "com.tyler.simplesurvey.first_result";
    public static final String EXTRA_SECOND_RESULT = "com.tyler.simplesurvey.second_result";

    private final String TAG = "Result Activity";

    private TextView mFirstAnswerText;
    private TextView mSecondAnswerText;
    private TextView mFirstScoreText;
    private TextView mSecondScoreText;
    private Button mResetButton;
    private Button mContinueButton;

    int firstScore = 0;
    int secondScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Log.i(TAG, "Result Activity Launched");

        //Get a reference to the intent that launched this activity
        Intent launchIntent = getIntent();
        //Get the Extra's that we added to the Intent in SimpleSurveyActivity
        String firstAnswer = launchIntent.getStringExtra(SimpleSurveyActivity.KEY_FIRST_ANSWER);
        String secondAnswer = launchIntent.getStringExtra(SimpleSurveyActivity.KEY_SECOND_ANSWER);
        firstScore = launchIntent.getIntExtra(SimpleSurveyActivity.EXTRA_FIRST_SCORE, 0);
        secondScore = launchIntent.getIntExtra(SimpleSurveyActivity.EXTRA_SECOND_SCORE, 0);

        //Get references to the widgets and display correct information
        mFirstAnswerText = (TextView) findViewById(R.id.answer1);
        mSecondAnswerText = (TextView) findViewById(R.id.answer2);
        mFirstScoreText = (TextView) findViewById(R.id.dog_score);
        mSecondScoreText = (TextView) findViewById(R.id.cat_score);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mContinueButton = (Button) findViewById(R.id.continue_button);

        mFirstAnswerText.setText(firstAnswer);
        mSecondAnswerText.setText(secondAnswer);
        mFirstScoreText.setText(String.valueOf(firstScore));
        mSecondScoreText.setText(String.valueOf(secondScore));

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
                resultIntent.putExtra(EXTRA_FIRST_RESULT, firstScore);
                resultIntent.putExtra(EXTRA_SECOND_RESULT, secondScore);
                //Set the result to be OK, and provide resultIntent
                setResult(RESULT_OK, resultIntent);
                //Close Activity
                finish();

            }
        });

        // Continue the survey without resetting any data
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_FIRST_RESULT, firstScore);
                resultIntent.putExtra(EXTRA_SECOND_RESULT, secondScore);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}

