package com.example.vince.assignment4;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class listAdapter extends ArrayAdapter<Team> {
    private Activity mainActivity;
    private ArrayList<Team> teams;

    public listAdapter(@NonNull Activity context, @NonNull List<Team> objects) {
        super(context, R.layout.list_element, objects);
        mainActivity = context;
        teams = (ArrayList<Team>) objects;
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null)
            view = mainActivity.getLayoutInflater().inflate(R.layout.list_element, parent, false);

        ((TextView) view.findViewById(R.id.textField1)).setText(teams.get(position).getCity());
        ((TextView) view.findViewById(R.id.textField2)).setText(teams.get(position).getName());
        return view;
    }
}
