package com.example.vince.app6;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText idField, nameField, markField;

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        markField.setText("");
        idField.requestFocus();
    }

    class dbAddStudent implements View.OnClickListener {
        dbAddStudent() {
        }

        @Override
        public void onClick(View v) {
            Student student = new Student(idField.getText().toString(), nameField.getText().toString(), markField.getText().toString());

            if (student.id.length() > 0 && student.id.length() <= 8 && student.name.length() > 0 && student.name.length() <= 8 && student.mark.length() > 0 && student.mark.length() <= 8) {
                MainActivity.this.db.execSQL("INSERT INTO students VALUES(?,?,?)", new Object[]{student.id, student.name, student.mark});

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setNeutralButton("CLOSE", null);
                StringBuffer sb = new StringBuffer();
                sb.append("ID:").append(student.id);
                sb.append(" Name:").append(student.name);
                sb.append(" Marks:").append(student.mark);
                dialog.setMessage(sb.toString());
                dialog.setTitle("The following Student was added");
                dialog.show();
                clearFields();

            } else {
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    class dbSelectAll implements View.OnClickListener {
        public dbSelectAll() {
        }

        @Override
        public void onClick(View v) {
            Cursor cursor = db.rawQuery("select * from students", null);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            if (cursor.getCount() == 0) {
                builder.setNeutralButton("CLOSE", null);
                builder.setTitle("No students were found");
                builder.show();
            } else {
                StringBuffer sb = new StringBuffer();
                while (cursor.moveToNext()) {
                    sb.append(String.format("ID:%s  Name:%s  Marks:%s\n", cursor.getString(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("mark"))));
                }
                builder.setNeutralButton("CLOSE", null);

                if (cursor.getCount() > 1)
                    builder.setTitle("The following students has been added");
                else
                    builder.setTitle("The following student has been added");
                builder.setMessage(sb);
                builder.show();
            }
            cursor.close();
        }
    }

    class dbFindStudentById implements View.OnClickListener {
        public dbFindStudentById() {
        }

        @Override
        public void onClick(View v) {
            Cursor cursor = db.rawQuery(String.format("select * from students where id ='%s'", MainActivity.this.idField.getText()), null);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setNeutralButton("CLOSE", null);

            if (cursor.getCount() == 0) {
                dialog.setTitle("This student does not exist");
            } else {
                cursor.moveToFirst();
                dialog.setTitle("Student details are as follows");
                dialog.setMessage(String.format("ID:%s Name:%s Marks:%s", cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("mark"))));
            }

            clearFields();
            dialog.show();
            cursor.close();
        }
    }

    class dbDeleteStudentById implements View.OnClickListener {
        public dbDeleteStudentById() {
        }

        @Override
        public void onClick(View v) {
            Cursor cursor = db.rawQuery(String.format("select * from students where id ='%s'", MainActivity.this.idField.getText()), null);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setNeutralButton("CLOSE", null);

            if (cursor.getCount() == 0) {
                dialog.setTitle("This student does not exist");
            } else {
                cursor.moveToFirst();
                dialog.setTitle("Student details are as follows");
                dialog.setMessage(String.format("ID:%s Name:%s Marks:%s", cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("mark"))));

                MainActivity.this.db.execSQL(String.format("delete from students where id='%s'", MainActivity.this.idField.getText()));
            }

            clearFields();
            dialog.show();
            cursor.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = openOrCreateDatabase("StudentData", 0, null);
        this.db.execSQL("create table if not exists students(id varchar, name varchar, mark varchar);");
        this.idField = findViewById(R.id.idField);
        this.nameField = findViewById(R.id.nameField);
        this.markField = findViewById(R.id.markField);

        findViewById(R.id.addStudentButton).setOnClickListener(new dbAddStudent()); //add student button
        findViewById(R.id.viewStudentsButton).setOnClickListener(new dbSelectAll()); //view all students button
        findViewById(R.id.findStudentByIdButton).setOnClickListener(new dbFindStudentById()); //find student by id button
        findViewById(R.id.deleteStudentByIdButton).setOnClickListener(new dbDeleteStudentById());
    }
}
