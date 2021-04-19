package com.example.guess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    private TextView mTextViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        mTextViewAnswer= findViewById(R.id.textViewAnswer);
        String answer= getIntent().getStringExtra("question_answer");
        if (answer != null)
            mTextViewAnswer.setText(answer);
    }
    public void onReturn(View view) {

        finish();
    }
}