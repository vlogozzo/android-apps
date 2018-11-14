package com.example.vince.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class add_team extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView city = findViewById(R.id.cityField);
                TextView name = findViewById(R.id.nameField);
                TextView sport = findViewById(R.id.sportField);
                TextView mvp = findViewById(R.id.mvpField);
                TextView stadium = findViewById(R.id.stadiumField);

                if (city.getText().length() > 0 && name.getText().length() > 0) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putParcelable("newTeam", new Team(
                            city.getText().toString(),
                            name.getText().toString(),
                            sport.getText().toString(),
                            mvp.getText().toString(),
                            stadium.getText().toString()
                    ));

                    intent.putExtra("newTeam", bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "City and Name are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
