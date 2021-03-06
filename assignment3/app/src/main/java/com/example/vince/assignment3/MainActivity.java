package com.example.vince.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int ADD_TEAM_REQUEST = 1;
    static final int UPDATE_TEAM_REQUEST = 2;
    appDB db = new appDB(this);
    ArrayList<Team> teams = new ArrayList<>();
    ArrayAdapter<Team> adapter;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_TEAM_REQUEST && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getBundleExtra("newTeam");
            Team temp = bundle.getParcelable("newTeam");

            teams.add(temp);
            adapter.notifyDataSetChanged();
        } else if (requestCode == UPDATE_TEAM_REQUEST && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getBundleExtra("updatedTeam");
            teams.set(bundle.getInt("index"), (Team) bundle.getParcelable("updatedTeam"));
            adapter.notifyDataSetChanged();
        } else if (resultCode == RESULT_CANCELED) {
            //activity sends nothing back (exit button hit)
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teams = db.getAllTeams();
        Log.d("checkout", "onCreate: "+String.valueOf(teams.size()));
        Team.setCount(teams.size());
        adapter = new listAdapter(this, teams);
        ListView listview = findViewById(R.id.listView);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, update_team.class);
                intent.putExtra("team", teams.get(position));
                intent.putExtra("index", position);
                startActivityForResult(intent, UPDATE_TEAM_REQUEST);
            }
        });

        findViewById(R.id.addTeamButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), add_team.class), ADD_TEAM_REQUEST);
            }
        });
        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.update_teams(teams);
    }
}
