package controller;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.convertering.R;

import java.util.ArrayList;
import java.util.Date;

import model.noteModel;
import utility.config;
import utility.util;

public class noteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<noteModel> arr;

    public noteAdapter(Context context, ArrayList<noteModel> arr) {
        this.context=context;
        this.arr=arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)arr.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(this.context);
        View rowView=(View) inflater.inflate(R.layout.note_layout, parent, false);

        TextView lblTitle= rowView.<TextView>findViewById(R.id.lbl_title);
        TextView lblContext= rowView.<TextView>findViewById(R.id.lbl_context);
        ImageView imgAttach= rowView.<ImageView>findViewById(R.id.imgView);
        TextView lblDay= rowView.<TextView>findViewById(R.id.lbl_day);
        TextView lblDate= rowView.<TextView>findViewById(R.id.lbl_date);
        TextView lblTime= rowView.<TextView>findViewById(R.id.lbl_time);

        noteModel model=arr.get(position);
        lblTitle.setText(model.title);
        lblContext.setText(model.content);

        Date date= util.convertStringToDate(model.datetime);
        String dayOfWeek= (String) DateFormat.format("EEEE", date);
        String stringMonth= (String) DateFormat.format("MMM", date);
        String day= (String) DateFormat.format("dd", date);
        String time= (String) DateFormat.format("hh:mm", date);

        lblDay.setText(dayOfWeek);
        lblDate.setText(day+" "+stringMonth);
        lblTime.setText(time);

        util.setBitmapToImage(context, config.FOLDER_IMAGE, model.image, imgAttach);

        return rowView;
    }
}
