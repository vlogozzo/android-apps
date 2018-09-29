package com.example.vince.app2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button userButton = findViewById(R.id.userButton);
        userButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              EditText userField = findViewById(R.id.userField);
                                              userButton.setText(userField.getText().toString());
                                          }
                                      }
        );

        final Button passButton = findViewById(R.id.passButton);
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText passField = findViewById(R.id.passField);
                passButton.setText(passField.getText().toString());
            }
        });

        final Button userPassButton = findViewById(R.id.userPassButton);
        userPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userField = findViewById(R.id.userField);
                EditText passField = findViewById(R.id.passField);
                userPassButton.setText(userField.getText().toString() + " AND " + passField.getText().toString());
            }
        });
    }
}
