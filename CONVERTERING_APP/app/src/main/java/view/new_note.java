package view;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.convertering.R;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import controller.noteDataSource;
import utility.config;
import utility.util;

public class new_note extends AppCompatActivity {

    public final int PICK_PHOTO_FOR_NOTE=0;
    Toolbar toolbar;
    ImageView imgAttach;
    Bitmap bmpAttach;
    EditText txtTitle;
    EditText txtContext;

    noteDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        addControls();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.add_note_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.save_note:
                if(txtTitle.getText().toString().trim().length()<=0) {
                    Toast.makeText(this, "Please Input the Note", Toast.LENGTH_LONG).show();
                    return true;
                }
                if(txtContext.getText().toString().trim().length()<=0) {
                    Toast.makeText(this, "Please Input the Content of Note", Toast.LENGTH_LONG).show();
                    return true;
                }
                String datetime= util.getCurrentTime();
                String imagename=util.convertStringDateTimeToFileName(datetime)+".png";

                dataSource.addNewNote(txtTitle.getText().toString(), txtContext.getText().toString(), imagename, datetime);

                if(bmpAttach!=null) {
                    util.saveImageToSDCard(bmpAttach, config.FOLDER_IMAGE, imagename);
                }
                this.finish();
                Toast.makeText(this, "Add new note successfully!!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.attach_imgae:
                pickImage();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_PHOTO_FOR_NOTE && requestCode== Activity.RESULT_OK) {
            if(data==null) {
                Toast.makeText(this, "There is no selected photo", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                InputStream inputStream=this.getContentResolver().openInputStream(data.getData());
                BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
                bmpAttach= BitmapFactory.decodeStream(bufferedInputStream);
                imgAttach.setImageBitmap(bmpAttach);
                imgAttach.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }

    private void pickImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PHOTO_FOR_NOTE);
    }

    private void addEvents() {


    }

    private void addControls() {
        txtTitle= this.<EditText>findViewById(R.id.txtTitle);
        txtContext= this.<EditText>findViewById(R.id.txtContext);
        imgAttach= this.<ImageView>findViewById(R.id.imgAttach);

        toolbar= this.<Toolbar>findViewById(R.id.myToolbar_2);
        toolbar.setTitle("New Note");
        setSupportActionBar(toolbar);

        dataSource=new noteDataSource(this);
        dataSource.open();
    }
}