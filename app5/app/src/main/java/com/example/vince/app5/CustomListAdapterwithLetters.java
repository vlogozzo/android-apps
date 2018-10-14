
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


public class CustomListAdapterwithLetters extends ArrayAdapter<textAndImage> {


    public CustomListAdapterwithLetters(@NonNull Context context, textAndImage[] data) {
        super(context, R.layout.list_element_icon_name, data);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = LayoutInflater.from(getContext()).inflate(R.layout.list_element_name_icon, parent, false);

        textAndImage asset = getItem(position);
        TextView field1 = customView.findViewById(R.id.textField1);
        ImageView icon = customView.findViewById(R.id.icon1);
        TextView letter = customView.findViewById(R.id.letterField);

        field1.setText(asset.getText());
        icon.setImageResource(asset.getImage());
        letter.setText(String.valueOf(asset.getLetter()));
        return customView;
    }
}
