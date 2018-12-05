package com.example.vince.assignment6;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private final String[] sorts = {"magnitude", "date"};
    int day, month, year;

    private String dateString() {
        return " " + year + "-" + (month + 1) + "-" + day;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        ((TextView) findViewById(R.id.dateField)).setText(dateString());

        ((Spinner) findViewById(R.id.sortSpinner)).setAdapter(
                new ArrayAdapter<>(
                        getApplication(),
                        android.R.layout.simple_spinner_dropdown_item,
                        sorts));

        findViewById(R.id.startDateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog date = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int nYear, int nMonth, int nDayOfMonth) {
                        year = nYear;
                        month = nMonth;
                        day = nDayOfMonth;
                        ((TextView) findViewById(R.id.dateField)).setText(dateString());
                    }
                }, day, month, year);
                date.getDatePicker().init(year, month, day, null);
                date.show();
            }
        });

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer url = new StringBuffer();
                url.append("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=7");

                url.append("&");
                url.append("starttime=" + year + "-" + (month + 1) + "-" + day);

                url.append("&");
                url.append("limit=" + ((EditText) findViewById(R.id.numberOfEarthQuakesField)).getText());

                url.append("&");
                String selectedItem = ((Spinner) findViewById(R.id.sortSpinner)).getSelectedItem().toString();
                if (selectedItem.equals("date")) //wording hack
                    selectedItem = "time";
                url.append("orderby=" + selectedItem);

                Intent intent = new Intent(getApplicationContext(), quakeList.class);
                intent.putExtra("url", url.toString());
                    startActivity(intent);
            }
        });
    }
}
