package com.example.vince.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class appDB extends SQLiteOpenHelper {

    appDB(Context context) {
        super(context, "appdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists teams(city TEXT, name TEXT PRIMARY KEY, sport TEXT, mvp TEXT, image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS teams");
        onCreate(db);
    }

    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> teams = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM teams", null);
        while (cursor.moveToNext()) {
            teams.add(new Team(
                    cursor.getString(cursor.getColumnIndex("city")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sport")),
                    cursor.getString(cursor.getColumnIndex("mvp")),
                    cursor.getString(cursor.getColumnIndex("image"))
            ));
        }
        return teams;
    }

    public void update_teams(ArrayList<Team> teams) {
        SQLiteDatabase db = getWritableDatabase();
        for (Team t : teams) {
            Log.d("inspect", "update_teams: " + t.toString());
            ContentValues values = new ContentValues();
            values.put("city", t.getCity());
            values.put("name", t.getName());
            values.put("sport", t.getSport());
            values.put("mvp", t.getMvp());
            values.put("image", t.getImage());
            db.insertWithOnConflict("teams", null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
        db.close();
    }

    public void delete_team(String name) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuilder sb = new StringBuilder().append("name = '").append(name).append("'");
        db.delete("teams", sb.toString(), null);
        db.close();
    }
}
