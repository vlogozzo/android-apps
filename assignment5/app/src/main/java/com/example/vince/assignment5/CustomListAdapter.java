package com.example.vince.assignment5;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


class CustomListAdapter extends ArrayAdapter<String> {
    Activity context;
    List<String> data;

    public CustomListAdapter(Activity activity, List<String> data) {
        super(activity, R.layout.single_element, data);
        this.context = activity;
        this.data = data;
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        View newView = this.context.getLayoutInflater().inflate(R.layout.single_element, null, true);
        StringTokenizer tokens = new StringTokenizer(this.data.get(position), "@@");
        String titleToken = tokens.nextToken();
        String timeToken = tokens.nextToken();
        final String urlToken = tokens.nextToken();


        ((TextView) newView.findViewById(R.id.Title)).setText(titleToken);
        ((TextView) newView.findViewById(R.id.Date)).setText(new Date(Long.parseLong(timeToken)).toString());

        if (position % 2 != 0)
            newView.findViewById(R.id.Layout).setBackgroundColor(Color.parseColor("#33b5e5"));
        newView.findViewById(R.id.Layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToken));
                context.startActivity(browserIntent);
            }
        });

        return newView;
    }
}