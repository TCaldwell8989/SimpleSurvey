package com.tyler.simplesurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleSurveyActivity extends AppCompatActivity {
    /// This program asks a question with two answers and displays current survey results

    public static final String EXTRA_FIRST_SCORE = "com.tyler.simplesurvey.dog_score";
    public static final String EXTRA_SECOND_SCORE = "com.tyler.simplesurvey.cat_score";
    private final int RESULTS_REQUEST_CODE = 0;
    private final int QUESTION_REQUEST_CODE = 0;

    private Button mDogButton;
    private Button mCatButton;
    private Button mResultsButton;
    private Button mQuestionConfigButton;


    int mFirstScore = 0;
    int mSecondScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_survey);

        // Check to see if there is any data in savedInstanceState
        if (savedInstanceState != null) {
            mFirstScore = savedInstanceState.getInt(EXTRA_FIRST_SCORE);
            mSecondScore = savedInstanceState.getInt(EXTRA_SECOND_SCORE);
        }

        mDogButton = (Button) findViewById(R.id.dog_button);
        mCatButton = (Button) findViewById(R.id.cat_button);
        mResultsButton = (Button) findViewById(R.id.results_button);
        mQuestionConfigButton = (Button) findViewById(R.id.question_config_button);

        // Add 1 to the dog score and update display
        mDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirstScore += 1;

            }
        });

        // Add 1 to the cat score and update display
        mCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSecondScore += 1;

            }
        });

        // Reset's the scores to 0
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchResultsActivity = new Intent(SimpleSurveyActivity.this, ResultsActivity.class);
                launchResultsActivity.putExtra(EXTRA_FIRST_SCORE, mFirstScore);
                launchResultsActivity.putExtra(EXTRA_SECOND_SCORE, mSecondScore);
                startActivityForResult(launchResultsActivity, RESULTS_REQUEST_CODE);

            }
        });

        mQuestionConfigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchQuestionActivity = new Intent(SimpleSurveyActivity.this, QuestionConfigurationActivity.class);
                startActivityForResult(launchQuestionActivity, QUESTION_REQUEST_CODE);
            }
        });
    }

    // Save the current scores to outBundle with their own key
    @Override
    protected void onSaveInstanceState (Bundle outBundle) {
        outBundle.putInt(EXTRA_FIRST_SCORE, mFirstScore);
        outBundle.putInt(EXTRA_SECOND_SCORE, mSecondScore);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Verify REQUEST_OK was the result, and what request ended up with this result
        if (requestCode == RESULTS_REQUEST_CODE && resultCode == RESULT_OK) {
            int firstScore = data.getIntExtra(EXTRA_FIRST_SCORE, 0);
            int secondScore = data.getIntExtra(EXTRA_SECOND_SCORE, 0);

            if (firstScore == 0 && secondScore == 0) {
                mFirstScore = firstScore;
                mSecondScore = secondScore;
                Toast.makeText(this, "Scores are currently at 0", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Your survey has been continued", Toast.LENGTH_LONG).show();
            }
        }
    }
}


