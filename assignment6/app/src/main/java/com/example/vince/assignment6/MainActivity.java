package com.example.vince.assignment6;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String[] sorts = {"magnitude", "date"};
    int day, month, year;


    class dateListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Spinner) findViewById(R.id.sortSpinner)).setAdapter(
                new ArrayAdapter<>(
                        getApplication(),
                        android.R.layout.simple_spinner_dropdown_item,
                        sorts));

        findViewById(R.id.startDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog();
            }
        });
    }

    protected Dialog onCreateDialog() {
        return new DatePickerDialog(this, new dateListener(), year, month, day);
    }
}
