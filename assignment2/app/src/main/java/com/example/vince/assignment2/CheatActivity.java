package com.example.vince.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private boolean cheated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);


        final Button answerButton = findViewById(R.id.showAnswerButton);
        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cheated = true;

                TextView answerField = findViewById(R.id.answerField);

                answerField.setText(String.valueOf(getIntent().getBooleanExtra("ANSWER", true))); //might not work
            }
        });

        final Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent().putExtra("cheat", cheated));
                finish();
            }
        });





    }
}
