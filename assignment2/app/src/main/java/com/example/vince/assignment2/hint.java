package com.example.vince.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class hint extends AppCompatActivity {
    private boolean usedHint = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);


        final Button hintButton = findViewById(R.id.showHintButton);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usedHint = true;

                TextView hintField = findViewById(R.id.hintField);
                hintField.setText(getIntent().getIntExtra("HINT", 0));
            }
        });

        final Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent().putExtra("hint", usedHint));
                finish();
            }
        });


    }
}
