package com.example.vince.app7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static SQLiteDatabase db;
    ArrayList<Employee> employeesL = new ArrayList<>();
    ArrayList<Employee> employeesR = new ArrayList<>();

    class dbAddEmployee implements View.OnClickListener {
        String table;

        public dbAddEmployee(String side) {
            this.table = side;
        }

        @Override
        public void onClick(View v) {
            EditText name;
            EditText dept;
            EditText year;
                name = findViewById(R.id.leftName);
                dept = findViewById(R.id.leftDept);
                year = findViewById(R.id.leftYear);

            if (table.equals("employeesR")) {
                name = findViewById(R.id.rightName);
                dept = findViewById(R.id.rightDept);
                year = findViewById(R.id.rightYear);
            }

            if (name.getText().length() > 0 && dept.getText().length() > 0 && year.getText().length() > 0 &&
                    !name.getText().toString().toUpperCase().contains("INSERT") &&
                    !dept.getText().toString().toUpperCase().contains("INSERT") &&
                    !year.getText().toString().toUpperCase().contains("INSERT")) {
                MainActivity.db.execSQL("INSERT INTO " + this.table + " VALUES(?,?,?)", new Object[]{name.getText(), dept.getText(), year.getText()});
                name.setText("");
                dept.setText("");
                year.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Incomplete", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class dbSelectAll implements View.OnClickListener {
        public dbSelectAll() {
        }

        @Override
        public void onClick(View v) {
            Cursor cursor = db.rawQuery("SELECT * FROM employeesL", null);
            while (cursor.moveToNext()) {
                employeesL.add(new Employee(
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("department")),
                        cursor.getString(cursor.getColumnIndex("year"))
                ));
            }
            cursor = db.rawQuery("SELECT * FROM employeesR", null);
            while (cursor.moveToNext()) {
                employeesR.add(new Employee(
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("department")),
                        cursor.getString(cursor.getColumnIndex("year"))
                ));
            }
            cursor.close();

            Intent intent = new Intent(MainActivity.this, list_display.class);

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("employeesL", employeesL);
            bundle.putParcelableArrayList("employeesR", employeesR);
            intent.putExtras(bundle);

            startActivity(intent);
            employeesL.clear();
            employeesR.clear();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = openOrCreateDatabase("employees", 0, null);
        this.db.execSQL("create table if not exists employeesL(name varchar, department varchar, year varchar);");
        this.db.execSQL("create table if not exists employeesR(name varchar, department varchar, year varchar);");

        findViewById(R.id.leftAddButton).setOnClickListener(new dbAddEmployee("employeesL"));
        findViewById(R.id.rightAddButton).setOnClickListener(new dbAddEmployee("employeesR"));
        findViewById(R.id.viewButton).setOnClickListener(new dbSelectAll());

    }
}
