package controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import model.noteModel;

public class noteDataSource {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private  String[] allColumns= { noteSQLHelper.COLUMN_ID,
            noteSQLHelper.COLUMN_TITLE,
            noteSQLHelper.COLUMN_NOTE,
            noteSQLHelper.COLUMN_DATETIME,
            noteSQLHelper.COLUMN_IMAGE};

    private Context context;

    public noteDataSource(Context context) {
        this.context=context;
        sqLiteOpenHelper=new noteSQLHelper(context);
    }

    public void open() throws SQLException {
        sqLiteDatabase=sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() throws  SQLException {
        sqLiteOpenHelper.close();
    }

    public void addNewNote(String title, String note, String image, String datetime) {
        ContentValues values = new ContentValues();
        values.put(noteSQLHelper.COLUMN_TITLE, title);
        values.put(noteSQLHelper.COLUMN_NOTE, note);
        values.put(noteSQLHelper.COLUMN_DATETIME, datetime);
        values.put(noteSQLHelper.COLUMN_IMAGE, image);

        sqLiteDatabase.insert(noteSQLHelper.TABLE_NAME, null, values);
    }

    public void deleteNote(int id) {
        sqLiteDatabase.delete(noteSQLHelper.TABLE_NAME, noteSQLHelper.COLUMN_ID+"="+id, null );
        Toast.makeText(this.context, "Delete Note success", Toast.LENGTH_LONG).show();
    }

    public ArrayList<noteModel> getAllNotes() {
        ArrayList<noteModel> arr=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(noteSQLHelper.TABLE_NAME, allColumns, null, null, null, null, null);
        if(cursor== null) {
            return  null;
        }
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            noteModel model=cursorToModel(cursor);
            arr.add(model);
            cursor.moveToNext();

        }

        return  arr;
    }

    private noteModel cursorToModel(Cursor cursor) {
        noteModel model=new noteModel();
        model.id=cursor.getInt(0);
        model.title=cursor.getString(1);
        model.content=cursor.getString(2);
        model.datetime=cursor.getString(3);
        model.image=cursor.getString(4);

        return model;
    }
}
