package controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class noteSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "mynotes.db";
    public static final String TABLE_NAME= "mydairy";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TITLE= "title";
    public static final String COLUMN_NOTE=" note";
    public static final String COLUMN_IMAGE= "image";
    public static final String COLUMN_DATETIME= "datetime";

    public static final int DATABASE_VERSION =1;
    public static final String CREATE_DATABASE= "CREATE TABLE " + TABLE_NAME + "( "
            + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement,"
            + COLUMN_TITLE + " text not null,"
            + COLUMN_NOTE + " text not null,"
            + COLUMN_DATETIME + " text not null,"
            + COLUMN_IMAGE + " text);";


    public noteSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+ TABLE_NAME);
        onCreate(db);
    }
}
