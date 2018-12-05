package com.example.vince.assignment6;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class quakeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quake_list);

        new QuakeAsyncTask().execute(getIntent().getStringExtra("url"));
    }

    class QuakeAsyncTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... stringURL) {
            return Utils.fetchEarthquakeData(stringURL[0]);
        }

        public void onPostExecute(List<String> postExecuteResult) {
            ArrayAdapter<String> arrayAdapter = new CustomListAdapter(quakeList.this, postExecuteResult);
            ((ListView) findViewById(R.id.listView)).setAdapter(arrayAdapter);
        }
    }
}
