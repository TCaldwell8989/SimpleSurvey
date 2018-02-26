package com.tyler.simplesurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionConfigurationActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_QUESTION = "com.tyler.simplesurvey.new_question";
    public static final String EXTRA_FIRST_ANSWER = "com.tyler.simplesurvey.first_answer";
    public static final String EXTRA_SECOND_ANSWER = "com.tyler.simplesurvey.second_answer";

    private final String TAG = "Question Activity";

    private EditText mNewQuestionBox;
    private EditText mFirstAnswerBox;
    private EditText mSecondAnswerBox;
    private Button mChangeQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_configuration);

        Log.i(TAG, "Question Activity Launched");

        // Get references to the widgets
        mNewQuestionBox = (EditText) findViewById(R.id.new_question);
        mFirstAnswerBox = (EditText) findViewById(R.id.first_answer);
        mSecondAnswerBox = (EditText) findViewById(R.id.second_answer);
        mChangeQuestionButton = (Button) findViewById(R.id.start_survey_button);

        // Verify user entered a new question and two new answers and send data back to SimpleSurveyActivity
        mChangeQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newQuestion = mNewQuestionBox.getText().toString();
                String firstAnswer = mFirstAnswerBox.getText().toString();
                String secondAnswer = mSecondAnswerBox.getText().toString();

                if (newQuestion.equals("") || firstAnswer.equals("") || secondAnswer.equals("")) {
                    Toast.makeText(QuestionConfigurationActivity.this, "Error: Please enter a mQuestion with two answers", Toast.LENGTH_LONG).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(EXTRA_NEW_QUESTION, newQuestion);
                    resultIntent.putExtra(EXTRA_FIRST_ANSWER, firstAnswer);
                    resultIntent.putExtra(EXTRA_SECOND_ANSWER, secondAnswer);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }
}
