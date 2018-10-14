package com.example.vince.app5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private textAndImage[] assets = new textAndImage[]{
            new textAndImage(R.drawable.cd, "CD"),
            new textAndImage(R.drawable.ez, "EZ"),
            new textAndImage(R.drawable.battery, "battery"),
            new textAndImage(R.drawable.blankapp, "blank\nApp"),
            new textAndImage(R.drawable.calender, "calender"),
            new textAndImage(R.drawable.capitaldcolon, "D:"),
            new textAndImage(R.drawable.download, "download"),
            new textAndImage(R.drawable.feelsbadman, "feelsbadman"),
            new textAndImage(R.drawable.feelsgoodman, "feelsgoodman"),
            new textAndImage(R.drawable.hypers, "hypers"),
            new textAndImage(R.drawable.mackey, "mac\noption\nkey"),
            new textAndImage(R.drawable.missme, "miss\nme")
    };

    private textAndImage[] assets2 = new textAndImage[]{
            new textAndImage(R.drawable.monkahmm, "monkaHmm", 'A'),
            new textAndImage(R.drawable.monkas, "monkaS", 'B'),
            new textAndImage(R.drawable.mouse, "mouse", 'C'),
            new textAndImage(R.drawable.poggers, "POGGERS", 'D'),
            new textAndImage(R.drawable.power, "power\nbutton", 'E'),
            new textAndImage(R.drawable.settings, "settings\nbutton", 'F'),
            new textAndImage(R.drawable.time, "clock", 'G'),
            new textAndImage(R.drawable.trash, "trash\nbin", 'H'),
            new textAndImage(R.drawable.vine, "vine\napp", 'I'),
            new textAndImage(R.drawable.whatsapp, "whatsApp", 'J'),
            new textAndImage(R.drawable.yelosad, "sad", 'K'),
            new textAndImage(R.drawable.yelotree, "happy", 'L')
    };


    class iconTextListener implements AdapterView.OnItemClickListener {
        public iconTextListener() {
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplication(), left_single_item.class);
            intent.putExtra("ICON", assets[position].getImage());
            intent.putExtra("TEXT", assets[position].getText());
            MainActivity.this.startActivity(intent);
        }
    }

    class textIconLetterListener implements AdapterView.OnItemClickListener {
        public textIconLetterListener() {
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplication(), right_single_item.class);
            intent.putExtra("ICON", assets2[position].getImage());
            intent.putExtra("TEXT", assets2[position].getText());
            intent.putExtra("LETTER", assets2[position].getLetter());
            MainActivity.this.startActivity(intent);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView leftList = findViewById(R.id.leftList);
        leftList.setAdapter(new CustomListAdapter(getApplicationContext(), assets));
        leftList.setOnItemClickListener(new iconTextListener());

        ListView rightList = findViewById(R.id.rightList);
        rightList.setAdapter(new CustomListAdapterwithLetters(getApplicationContext(), assets2));
        rightList.setOnItemClickListener(new textIconLetterListener());

    }
}
