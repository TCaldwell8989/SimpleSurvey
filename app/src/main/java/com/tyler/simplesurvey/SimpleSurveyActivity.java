package com.tyler.simplesurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleSurveyActivity extends AppCompatActivity {
    /// This program asks a question with two answers and displays current survey results

    Button mDogButton;
    Button mCatButton;
    Button mResetButton;
    TextView mDogScore;
    TextView mCatScore;

    int mDogTally = 0;
    int mCatTally = 0;


    private final static String Dog_Score_Key = "dog score bundle key";
    private final static String Cat_Score_Key = "cat score bundle key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_survey);

        // Check to see if there is any data in savedInstanceState
        if (savedInstanceState != null) {
            mDogTally = savedInstanceState.getInt(Dog_Score_Key);
            mCatTally = savedInstanceState.getInt(Cat_Score_Key);
        }

        mDogButton = (Button) findViewById(R.id.dog_button);
        mCatButton = (Button) findViewById(R.id.cat_button);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mDogScore = (TextView) findViewById(R.id.dog_score);
        mCatScore = (TextView) findViewById(R.id.cat_score);

        // Update the results, so data is displayed
        mDogScore.setText(String.valueOf(mDogTally));
        mCatScore.setText(String.valueOf(mCatTally));

        // Add 1 to the dog score and update display
        mDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDogTally += 1;
                mDogScore.setText(String.valueOf(mDogTally));

            }
        });

        // Add 1 to the cat score and update display
        mCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCatTally += 1;
                mCatScore.setText(String.valueOf(mCatTally));

            }
        });

        // Reset's the scores to 0
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDogScore.setText("0");
                mCatScore.setText("0");

            }
        });
    }

    // Save the current scores to outBundle with their own key
    @Override
    protected void onSaveInstanceState (Bundle outBundle) {
        outBundle.putInt(Dog_Score_Key, mDogTally);
        outBundle.putInt(Cat_Score_Key, mCatTally);

    }
}


