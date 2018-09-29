package com.example.vince.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] abcList = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String[] sevenList = new String[99];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView abc = findViewById(R.id.listView);
        GridView sevens = findViewById(R.id.gridView);

        for (int i = 0; i < this.sevenList.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append((i + 1) * 7);
            sevenList[i] = sb.toString();
        }


        abc.setAdapter(new ArrayAdapter(this, R.layout.list_element, abcList));
        sevens.setAdapter(new ArrayAdapter(this, R.layout.grid_element, sevenList));
    }
}
