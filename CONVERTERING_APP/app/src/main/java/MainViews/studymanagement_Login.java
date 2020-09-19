package MainViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.convertering.R;

public class studymanagement_Login extends AppCompatActivity {

    //2 TextView hiện tiêu đề
    TextView txtLogin, txtStudyManagement;

    //2 Button Student và Teacher
    ImageButton btnStudent, btnTeacher;

    //Button Back
    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement__login);
        hideActionBar();
        addControls();
        addEvents();
    }

    private void addEvents() {

        //Băt sự kiện Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backManHinh();
            }
        });

        //Bắt sự kiện Button Student
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhStudy();
            }
        });

        //Bắt sự kiện Button Teacher
        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhTeacher();
            }
        });
    }

    private void moManHinhTeacher() {
        Intent intentTeacher=new Intent(studymanagement_Login.this, manHinhStudy_2.class);
        startActivity(intentTeacher);
    }

    private void moManHinhStudy() {
        Intent intentStudent=new Intent(studymanagement_Login.this, manHinhStudy.class);
        startActivity(intentStudent);
    }


    private void backManHinh() {
        finish();
    }

    private void addControls() {

        //Khai báo 2 TextView hiện tiêu đề và cài đặt Typeface
        txtLogin= this.<TextView>findViewById(R.id.txtLogin);
        txtStudyManagement= this.<TextView>findViewById(R.id.txtForStudy);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtStudyManagement.setTypeface(typeface);
        txtLogin.setTypeface(typeface);

        //Khai báo 2 Button Student và Teacher
        btnStudent= this.<ImageButton>findViewById(R.id.btnStudent);
        btnTeacher= this.<ImageButton>findViewById(R.id.btnTeachet);

        //Khai báo Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);

    }

    private void hideActionBar() {

    }
}
