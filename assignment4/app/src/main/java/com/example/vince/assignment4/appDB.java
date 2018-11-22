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

    public void getAllTeams(ArrayList<Team> teams) {
        teams.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM teams", null);
        while (cursor.moveToNext()) {
            teams.add(new Team(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("city")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("sport")),
                    cursor.getString(cursor.getColumnIndex("mvp")),
                    cursor.getString(cursor.getColumnIndex("image"))));
        }
        db.close();
    }

    public void update_team(Team team) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        if (team.getId() != 0) {
            values.put("id", team.getId());
        }
        values.put("city", team.getCity());
        values.put("name", team.getName());
        values.put("sport", team.getSport());
        values.put("mvp", team.getMvp());
        values.put("image", team.getImage());
        db.insertWithOnConflict("teams", null, values, 5);
        db.close();
    }

    public void delete_team(int index) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("teams", "id="+index, null);
        db.close();
    }
}
