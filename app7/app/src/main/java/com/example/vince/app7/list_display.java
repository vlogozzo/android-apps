package com.example.vince.app7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class list_display extends AppCompatActivity {
    ArrayList<Employee> employeesL = new ArrayList<>();
    ArrayList<Employee> employeesR = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        Bundle bundle = getIntent().getExtras();
        employeesL = bundle.getParcelableArrayList("employeesL");
        employeesR = bundle.getParcelableArrayList("employeesR");



        ListView leftList = findViewById(R.id.listViewLeft);
        leftList.setAdapter(new listAdapter(getApplicationContext(), employeesL));
        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(list_display.this, expanded_element.class);
                Bundle bundle = new Bundle();

                bundle.putParcelable("employee", employeesL.get(position));
                bundle.putString("color", "#ff4444");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        ListView rightList = findViewById(R.id.listViewRight);
        rightList.setAdapter(new listAdapter(getApplicationContext(), employeesR));
        rightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(list_display.this, expanded_element.class);
                Bundle bundle = new Bundle();

                bundle.putParcelable("employee", employeesR.get(position));
                bundle.putString("color", "#ffbb32");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}