package com.example.vince.app5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends ArrayAdapter<textAndImage> {

    public CustomListAdapter(@NonNull Context context, textAndImage[] data) {
        super(context, R.layout.list_element_icon_name, data);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = LayoutInflater.from(getContext()).inflate(R.layout.list_element_icon_name, parent, false);

        textAndImage asset = getItem(position);
        ImageView icon = customView.findViewById(R.id.icon1);
        TextView field1 = customView.findViewById(R.id.textField1);

        icon.setImageResource(asset.getImage());
        field1.setText(asset.getText());
        return customView;
    }
}
