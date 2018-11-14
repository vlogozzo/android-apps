package com.example.vince.app7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class listAdapter extends ArrayAdapter<Employee> {
    public listAdapter(@NonNull Context context, List<Employee> data) {
        super(context, R.layout.list_element, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newView = LayoutInflater.from(getContext()).inflate(R.layout.list_element, parent, false);


        Employee asset = getItem(position);
        TextView text = newView.findViewById(R.id.textField1);
        text.setText(asset.getName());

        return newView;
    }
}
