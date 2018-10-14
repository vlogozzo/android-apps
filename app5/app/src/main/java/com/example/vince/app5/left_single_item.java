package com.example.vince.app5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class left_single_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_single_item);

        ImageView icon = findViewById(R.id.icon1);
        icon.setImageResource(getIntent().getIntExtra("ICON", 0));
        TextView textFiled = findViewById(R.id.textField1);
        textFiled.setText(getIntent().getStringExtra("TEXT"));
    }
}
