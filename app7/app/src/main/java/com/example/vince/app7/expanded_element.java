package com.example.vince.app7;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class expanded_element extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_element);


        Bundle bundle = getIntent().getExtras();
        findViewById(R.id.page).setBackgroundColor(Color.parseColor(bundle.getString("color")));
        Employee employee = bundle.getParcelable("employee");

        TextView name = findViewById(R.id.nameField);
        name.setText(employee.getName());

        TextView dept = findViewById(R.id.deptField);
        dept.setText(employee.getDept());

        TextView year = findViewById(R.id.yearField);
        year.setText(employee.getYear());
    }
}
