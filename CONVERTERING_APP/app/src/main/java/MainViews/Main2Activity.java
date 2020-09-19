package MainViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.convertering.R;
import Tools.toolss_Note;

import java.util.HashSet;

public class Main2Activity extends AppCompatActivity {

    EditText txtNhapVao;
    int noteID;

    TextView txtNewNote;

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
        addEvents();
    }

    private void addEvents() {

        txtNhapVao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                toolss_Note.arrNote.set(noteID, String.valueOf(s));
                toolss_Note.adapterNote.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.convertering.tools", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(toolss_Note.arrNote);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void addControls() {

        txtNhapVao= this.<EditText>findViewById(R.id.txtNhapVao);

        Intent intent=getIntent();
        noteID=intent.getIntExtra("noteId", -1);

        if(noteID != -1) {
            txtNhapVao.setText(toolss_Note.arrNote.get(noteID));
        }
        else {
             toolss_Note.arrNote.add("");
             noteID=toolss_Note.arrNote.size()-1;
             toolss_Note.adapterNote.notifyDataSetChanged();
        }

        //Khai bao TextView New Note;
        txtNewNote= this.<TextView>findViewById(R.id.txtNewNote);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtNewNote.setTypeface(typeface);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);
    }
}
