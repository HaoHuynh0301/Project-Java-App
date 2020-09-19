package view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.convertering.R;

import controller.noteDataSource;
import model.noteModel;
import utility.config;
import utility.util;

import static view.studymanagement_student_diary.adapter;

public class view_note extends AppCompatActivity {
    model.noteModel noteModel;
    Toolbar toolbar;
    TextView txtContext;
    ImageView imgAttach;
    noteDataSource dataSource;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {

        //get id of note
        noteId=getIntent().getExtras().getInt("note_id");
        String title=getIntent().getExtras().getString("title");
        String content=getIntent().getExtras().getString("content");
        String datetime=getIntent().getExtras().getString("date");
        String image=getIntent().getExtras().getString("image");

        //get ActionBar
        toolbar= this.<Toolbar>findViewById(R.id.myToolbar_3);
        toolbar.setTitle(title);
        toolbar.setSubtitle(datetime);
        setSupportActionBar(toolbar);

        //finds Views
        txtContext= this.<TextView>findViewById(R.id.txtContext);
        imgAttach= this.<ImageView>findViewById(R.id.imgAttach);

        txtContext.setText(content);
        util.setBitmapToImage(this, config.FOLDER_IMAGE, image, imgAttach);

        //create data source
        dataSource=new noteDataSource(this);
        dataSource.open();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.view_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                dataSource.deleteNote(noteId);
                adapter.notifyDataSetChanged();
                this.finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
    }


}

