package view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.convertering.R;

import java.util.ArrayList;

import controller.noteAdapter;
import controller.noteDataSource;
import model.noteModel;

public class studymanagement_student_diary extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Toolbar myToolBar;

    ImageButton btnBack;

    ListView lvTimKiem;
    noteDataSource dataSource;
    static ArrayList<noteModel> arrNote=new ArrayList<>();
    public static noteAdapter adapter;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement_student_diary);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        addControls();
        addEvents();

    }

    private void viewAllNotes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                arrNote=dataSource.getAllNotes();
                handler.sendEmptyMessage(0);
            }
        }).start();
    }


    private void addEvents() {


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(studymanagement_student_diary.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có muốn thoát ứng dụng hay không");
                b.setIcon(R.drawable.backiconyellow48);
                b.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();

            }
        });
    }

    private void addControls() {

        lvTimKiem= this.<ListView>findViewById(R.id.lvHienThi);
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);

        myToolBar= this.<Toolbar>findViewById(R.id.myToolbar);
        myToolBar.setTitle("My Diary");
        setSupportActionBar(myToolBar);


        dataSource=new noteDataSource(this);
        dataSource.open();


        adapter=new noteAdapter(this, arrNote);
        lvTimKiem.setAdapter(adapter);

        handler=new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(arrNote!=null && arrNote.size()>0) {
                    noteAdapter adapter=new noteAdapter(studymanagement_student_diary.this, arrNote);
                    lvTimKiem.setAdapter(adapter);
                }
            }
        };


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_note:
                Intent intentNewNote=new Intent(studymanagement_student_diary.this, new_note.class);
                startActivity(intentNewNote);
                break;
            case R.id.setting:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewAllNotes();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this, view_note.class);
        intent.putExtra("note_id", arrNote.get(position).id);
        intent.putExtra("title", arrNote.get(position).title);
        intent.putExtra("content", arrNote.get(position).content);
        intent.putExtra("date", arrNote.get(position).datetime);
        intent.putExtra("image", arrNote.get(position).image);

        startActivity(intent);
    }
}

