package com.tyler.simplesurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
This program asks a question with two answers. It allows results to be displayed, reset,
and continued. It allows user to change survey question and answers.
 */
public class SimpleSurveyActivity extends AppCompatActivity {

    // GLOBAL VARIABLES
    public static final String EXTRA_FIRST_SCORE = "com.tyler.simplesurvey.first_score";
    public static final String EXTRA_SECOND_SCORE = "com.tyler.simplesurvey.second_score";
    public static final String KEY_FIRST_SCORE = "first score bundle key";
    public static final String KEY_SECOND_SCORE = "second score bundle key";
    public static final String KEY_QUESTION = "mQuestion bundle key";
    public static final String KEY_FIRST_ANSWER = "first answer bundle key";
    public static final String KEY_SECOND_ANSWER = "second answer bundle key";

    private final String TAG  = "Survey Activity";
    private final int RESULTS_REQUEST_CODE = 0;
    private final int QUESTION_REQUEST_CODE = 1;

    private TextView mQuestionText;
    private Button mFirstButton;
    private Button mSecondButton;
    private Button mResultsButton;
    private Button mQuestionConfigButton;

    int mFirstScore = 0;
    int mSecondScore = 0;
    String mQuestion = "Are you a dog or cat person?";
    String mAnswer1 = "Dog";
    String mAnswer2 = "Cat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_survey);

        // Check to see if there is any data in savedInstanceState and Log the current question
        if (savedInstanceState != null) {
            mFirstScore = savedInstanceState.getInt(KEY_FIRST_SCORE);
            mSecondScore = savedInstanceState.getInt(KEY_SECOND_SCORE);
            mQuestion = savedInstanceState.getString(KEY_QUESTION);
            mAnswer1 = savedInstanceState.getString(KEY_FIRST_ANSWER);
            mAnswer2 = savedInstanceState.getString(KEY_SECOND_ANSWER);
            Log.i(TAG, mQuestion);
        }

        //Get references to the widgets and display correct information
        mQuestionText = (TextView) findViewById(R.id.question_textview);
        mFirstButton = (Button) findViewById(R.id.dog_button);
        mSecondButton = (Button) findViewById(R.id.cat_button);
        mResultsButton = (Button) findViewById(R.id.results_button);
        mQuestionConfigButton = (Button) findViewById(R.id.question_config_button);

        mQuestionText.setText(mQuestion);
        mFirstButton.setText(mAnswer1);
        mSecondButton.setText(mAnswer2);

        // Add 1 to the first score and update display
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirstScore += 1;

            }
        });

        // Add 1 to the second score and update display
        mSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSecondScore += 1;

            }
        });

        // Launches new Activity with results displayed
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent launchResultsActivity = new Intent(SimpleSurveyActivity.this, ResultsActivity.class);
                launchResultsActivity.putExtra(EXTRA_FIRST_SCORE, mFirstScore);
                launchResultsActivity.putExtra(EXTRA_SECOND_SCORE, mSecondScore);
                launchResultsActivity.putExtra(KEY_FIRST_ANSWER, mAnswer1);
                launchResultsActivity.putExtra(KEY_SECOND_ANSWER, mAnswer2);
                startActivityForResult(launchResultsActivity, RESULTS_REQUEST_CODE);

            }
        });

        // Launches new Activity that allows user to change question and answers
        mQuestionConfigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstScore = 0;
                mSecondScore = 0;
                Intent launchQuestionActivity = new Intent(SimpleSurveyActivity.this, QuestionConfigurationActivity.class);
                startActivityForResult(launchQuestionActivity, QUESTION_REQUEST_CODE);
            }
        });
    }

    // Save the current scores, question, and answers to outBundle with their own key
    @Override
    protected void onSaveInstanceState (Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putInt(KEY_FIRST_SCORE, mFirstScore);
        outBundle.putInt(KEY_SECOND_SCORE, mSecondScore);
        outBundle.putString(KEY_QUESTION, (mQuestionText.getText().toString()));
        outBundle.putString(KEY_FIRST_ANSWER, (mFirstButton.getText().toString()));
        outBundle.putString(KEY_SECOND_ANSWER, (mSecondButton.getText().toString()));

    }

    //Verify results by verifying REQUEST_OK was the result, and what request ended up with this result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULTS_REQUEST_CODE && resultCode == RESULT_OK) {
            int firstScore = data.getIntExtra(ResultsActivity.EXTRA_FIRST_RESULT, 0);
            int secondScore = data.getIntExtra(ResultsActivity.EXTRA_SECOND_RESULT, 0);

            if (firstScore == 0 && secondScore == 0) {
                mFirstScore = firstScore;
                mSecondScore = secondScore;
                Toast.makeText(this, "Scores are currently at 0", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Your survey has been continued", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == QUESTION_REQUEST_CODE && resultCode == RESULT_OK) {
            String newQuestion = data.getStringExtra(QuestionConfigurationActivity.EXTRA_NEW_QUESTION);
            mAnswer1 = data.getStringExtra(QuestionConfigurationActivity.EXTRA_FIRST_ANSWER);
            mAnswer2 = data.getStringExtra(QuestionConfigurationActivity.EXTRA_SECOND_ANSWER);

            mQuestionText.setText(newQuestion);
            mFirstButton.setText(mAnswer1);
            mSecondButton.setText(mAnswer2);
        }
    }
}


