package com.example.vince.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup leftRG = findViewById(R.id.left_radio_group);
        leftRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton temp = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), temp.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        Button sayHi = findViewById(R.id.leftSayButton);
        sayHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = findViewById(R.id.left_header);
                if (!(leftRG.getCheckedRadioButtonId() == -1)) {
                    RadioButton rb = findViewById(leftRG.getCheckedRadioButtonId());

                    temp.setText("Hi " + rb.getText().toString());
                }
            }
        });

        final RadioGroup rightRG = findViewById(R.id.right_radio_group);
        rightRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton temp = findViewById(checkedId);
                Toast.makeText(getApplicationContext(), temp.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        Button sayHello = findViewById(R.id.rightSayButton);
        sayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = findViewById(R.id.right_header);
                if (!(rightRG.getCheckedRadioButtonId() == -1)) {
                    RadioButton rb = findViewById(rightRG.getCheckedRadioButtonId());

                    temp.setText("Hello " + rb.getText().toString());
                }
            }
        });

        final CheckBox red = findViewById(R.id.Red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (red.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Red", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final CheckBox yellow = findViewById(R.id.Yellow);
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yellow.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Yellow", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final CheckBox green = findViewById(R.id.Green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (green.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Green", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Button color = findViewById(R.id.colourButton);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer colors = new StringBuffer();
                if (red.isChecked()) colors.append(" Red");
                if (yellow.isChecked()) colors.append(" Yellow");
                if (green.isChecked()) colors.append(" Green");

                Toast.makeText(getApplicationContext(), colors, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
