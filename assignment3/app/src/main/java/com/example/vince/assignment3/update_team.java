package com.example.vince.assignment3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class update_team extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_team);


        Team team = getIntent().getParcelableExtra("team");

        ((TextView) findViewById(R.id.nameField)).setText(team.getName());
        ((TextView) findViewById(R.id.cityField)).setText(team.getCity());
        ((TextView) findViewById(R.id.sportField)).setText(team.getSport());
        ((TextView) findViewById(R.id.mvpField)).setText(team.getMvp());
        ((TextView) findViewById(R.id.stadiumField)).setText(team.getStadium());

        findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView city = findViewById(R.id.cityField);
                TextView name = findViewById(R.id.nameField);
                TextView sport = findViewById(R.id.sportField);
                TextView mvp = findViewById(R.id.mvpField);
                TextView stadium = findViewById(R.id.stadiumField);

                if (city.getText().length() > 0 && name.getText().length() > 0) {
                    Bundle bundle = new Bundle();

                    bundle.putParcelable("updatedTeam", new Team(
                            city.getText().toString(),
                            name.getText().toString(),
                            sport.getText().toString(),
                            mvp.getText().toString(),
                            stadium.getText().toString()
                    ));
                    bundle.putInt("index", getIntent().getIntExtra("index", -1));

                    setResult(RESULT_OK, new Intent().putExtra("updatedTeam", bundle));
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
