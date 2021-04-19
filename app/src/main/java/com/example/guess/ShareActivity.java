package com.example.guess;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ShareActivity extends AppCompatActivity {
    private String mQuestionText;
    public EditText edit_text_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        edit_text_name= findViewById(R.id.edit_text_name);
        mQuestionText= getIntent().getStringExtra("question text extra");

        SharedPreferences sharedPreferences= getSharedPreferences("app-pref", MODE_PRIVATE);
        String questiontitle= sharedPreferences.getString("share title", "");
        edit_text_name.setText(questiontitle);
    }
    public void onShare (View view) {
        String questiontitle= edit_text_name.getText().toString();
        Intent shareIntent= new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, questiontitle + "\n" + mQuestionText);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", questiontitle);
        editor.apply();

    }
}