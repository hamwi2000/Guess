package com.example.guess;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuestionActivity extends AppCompatActivity {

    private TextView mQues;

    private String[] QUESTIONS;

    private static final boolean[] ANSWERS = {
            false,
            true,
            true,
            false,
            true,
            false,
            false,
            false,
            false,
            true,
            true,
            false,
            true
    };

    private String[] ANSWERS_DETAILS;
    private String McQ, McAD;
    private boolean McA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences= getSharedPreferences("app_pref", MODE_PRIVATE);
        String appLang= sharedPreferences.getString("app_lang", "");
        if (!appLang.equals(""))
            com.barmej.guesstheanswer.LocaleHelper.setLocale(this, appLang);


        setContentView(R.layout.activity_question);
        mQues= findViewById(R.id.textViewQuestion);
        QUESTIONS= getResources().getStringArray(R.array.questions);
        ANSWERS_DETAILS= getResources().getStringArray(R.array.answers_details);
        showNewQuestion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuChangeLang) {
            showlanguageDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    private void showlanguageDialog() {
        AlertDialog alertDialog= new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.Languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                            case 2:
                                language = "fr";
                                break;
                        }
                        saveLanguage(language);

                        com.barmej.guesstheanswer.LocaleHelper.setLocale(QuestionActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).create();
        alertDialog.show();
    }

    private void saveLanguage(String lang){
        SharedPreferences sharedPreferences= getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("app_lang",lang);
        editor.apply();
    }

    private void showNewQuestion() {
        Random random= new Random();
        int randomQuestionIndex= random.nextInt(QUESTIONS.length);
        McQ= QUESTIONS[randomQuestionIndex];
        McA= ANSWERS[randomQuestionIndex];
        McAD= ANSWERS_DETAILS[randomQuestionIndex];
        mQues.setText(McQ);
    }
    public void onChangeQuestionClicked(View view) {
        showNewQuestion();
    }
    public void onTrueClicked (View view) {
        if (McA == true) {
            Toast.makeText(this, "إجابة صحيحة", Toast.LENGTH_SHORT).show();
            showNewQuestion();
        } else {
            Toast.makeText(this, "إجابة خاطئة", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(QuestionActivity.this, AnswerActivity.class);
            intent.putExtra("question_answer", McAD);
            startActivity(intent);
        }
    }
    public void onFalseClicked (View view) {
        if (McA == false) {
            Toast.makeText(this, "إجابة صحيحة", Toast.LENGTH_SHORT).show();
            showNewQuestion();
        } else {
            Toast.makeText(this, "إجابة خاطئة", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(QuestionActivity.this, AnswerActivity.class);
            intent.putExtra("question_answer", McAD);
            startActivity(intent);
        }
    }
        public void onShare (View view) {
            Intent intent= new Intent(QuestionActivity.this, ShareActivity.class);
            intent.putExtra("question text extra", McQ);
            startActivity(intent);
        }
    }
