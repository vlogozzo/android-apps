package com.example.vince.app5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class right_single_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_single_item);

        TextView textFiled1 = findViewById(R.id.textField1);
        textFiled1.setText(getIntent().getStringExtra("TEXT"));

        ImageView icon  = findViewById(R.id.icon1);
        icon.setImageResource(getIntent().getIntExtra("ICON", 0));

        TextView letter = findViewById(R.id.letter);
        letter.setText(String.valueOf(getIntent().getCharExtra("LETTER", '0')));
    }
}
