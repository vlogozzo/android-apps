package com.example.vince.assignment4;

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
    static final String[] sports = {"", "Baseball", "Basketball", "Football", "Hockey"};
    private appDB db = new appDB(this);
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayAdapter<Team> adapter;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == ADD_TEAM_REQUEST && resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getBundleExtra("newTeam");
                Team temp = bundle.getParcelable("newTeam");
                db.update_team(temp);
                teams.clear();
                db.getAllTeams(teams);
            } else if (requestCode == UPDATE_TEAM_REQUEST && resultCode == RESULT_OK && data != null) {
                Bundle bundle = data.getBundleExtra("updatedTeam");
                teams.set(bundle.getInt("index"), (Team) bundle.getParcelable("updatedTeam"));
                db.update_team((Team) bundle.getParcelable("updatedTeam"));
            } else if (requestCode == UPDATE_TEAM_REQUEST && resultCode == 2 && data != null) {
                Bundle bundle = data.getBundleExtra("team");
                Team temp = bundle.getParcelable("team");
                db.delete_team(temp.getId());

                for (int i = 0; i < teams.size(); i++)
                    if (teams.get(i).getId() == temp.getId()) {
                        teams.remove(i);
                        break;
                    }
            }
            adapter.notifyDataSetChanged();
            for (Team t : teams)
                Log.d("inspect", "onCreate: " + t.toString());
            Log.d("inspect", "onActivityResult: adapter updated"+teams.size());

        } else if (resultCode == RESULT_CANCELED) {
            //activity sends nothing back (exit button hit)
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db.getAllTeams(teams);

        for (Team t : teams)
            Log.d("inspect", "onCreate: " + t.toString());
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
}

