package Tools;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.convertering.R;

import java.util.ArrayList;
import java.util.HashSet;

import MainViews.Main2Activity;

public class toolss_Note extends AppCompatActivity {

    //TextView Note
    TextView txtNote;

    //Toolbar
    Toolbar toolbar;

    //ListView Hien Thi
    ListView lvHienThi;
    public static ArrayList<String> arrNote=new ArrayList<>();
    public static ArrayAdapter<String> adapterNote;

    //Button Back
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolss__note);
        addControls();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_noteSpe) {
            Intent intent=new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);

            return true;

        }

        return false;
    }

    private void addEvents() {

        //Bat su kien de mo man hinh Editor
        lvHienThi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("noteId", position);
                startActivity(intent);
            }
        });

        //Bat su kien Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvHienThi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final int deleteID=position;
                AlertDialog.Builder b=new AlertDialog.Builder(toolss_Note.this);
                b.setTitle("Thông báo");
                b.setMessage("Bạn có chắc muốn xoá ghi chú này không");
                b.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrNote.remove(deleteID);
                        adapterNote.notifyDataSetChanged();

                        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.convertering.tools", Context.MODE_PRIVATE);
                        HashSet<String> set=new HashSet<>(toolss_Note.arrNote);
                        sharedPreferences.edit().putStringSet("notes", set).apply();
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
                return true;
            }
        });
    }

    private void addControls() {

        //Khai bao TextView Note va cai dat Typeface
        txtNote= this.<TextView>findViewById(R.id.txtNote);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtNote.setTypeface(typeface);

        //Khai bao toolBar
        toolbar= this.<Toolbar>findViewById(R.id.myToolBarNote);
        toolbar.setTitle("Note");
        setSupportActionBar(toolbar);

        //Khai bao ListView Hien Thi
        lvHienThi= this.<ListView>findViewById(R.id.lvHienThi);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.convertering.tools", Context.MODE_PRIVATE);
        HashSet<String> set=(HashSet<String>) sharedPreferences.getStringSet("notes", null);
        if(set==null) {

        }
        else {
            arrNote=new ArrayList<>(set);
        }

        adapterNote=new ArrayAdapter<>(toolss_Note.this, android.R.layout.simple_dropdown_item_1line, arrNote);
        lvHienThi.setAdapter(adapterNote);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);
    }
}
