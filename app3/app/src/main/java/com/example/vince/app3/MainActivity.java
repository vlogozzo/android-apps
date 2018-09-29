package com.example.vince.app3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // username activity
        final Button usernameButton = findViewById(R.id.usernameButton);
        usernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
                intent.putExtra("USERNAME", "Username is " + username.getText().toString());
                startActivity(intent);
            }
        });

        // password activity
        final Button passwordButton = findViewById(R.id.passwordButton);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityThree.class);
                intent.putExtra("PASSWORD", "Password is " + password.getText().toString());
                startActivity(intent);
            }
        });

        final Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityFour.class);
                intent.putExtra("USERNAME", "Username is " + username.getText().toString());
                intent.putExtra("PASSWORD", "Password is " + password.getText().toString());
                startActivity(intent);
            }
        });
    }
}