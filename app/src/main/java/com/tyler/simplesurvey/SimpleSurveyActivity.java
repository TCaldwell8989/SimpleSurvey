package com.tyler.simplesurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleSurveyActivity extends AppCompatActivity {

    Button mDogButton;
    Button mCatButton;
    Button mResetButton;
    TextView mDogScore;
    TextView mCatScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_survey);

        mDogButton = (Button) findViewById(R.id.dog_button);
        mCatButton = (Button) findViewById(R.id.cat_button);
        mResetButton = (Button) findViewById(R.id.reset_button);
        mDogScore = (TextView) findViewById(R.id.dog_score);
        mCatScore = (TextView) findViewById(R.id.cat_score);

        mDogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateScore(mDogScore);

            }
        });

        mCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateScore(mCatScore);

            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDogScore.setText("0");
                mCatScore.setText("0");

            }
        });
    }

    private void updateScore(TextView tv) {
        int num = Integer.parseInt(tv.getText().toString());
        num += 1;
        tv.setText(String.valueOf(num));
    }
}
