package com.example.vince.assignment5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView linearLayoutListView;

    String stringURL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=6.0&limit=20";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuakeAsyncTask task = new QuakeAsyncTask();
        task.execute(stringURL);
    }

    class QuakeAsyncTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... stringURL) {
            return Utils.fetchEarthquakeData(stringURL[0]);
        }

        public void onPostExecute(List<String> postExecuteResult) {
            ArrayAdapter<String> arrayAdapter = new CustomListAdapter(MainActivity.this, postExecuteResult);
            linearLayoutListView = findViewById(R.id.listView);
            linearLayoutListView.setAdapter(arrayAdapter);
        }
    }
}
