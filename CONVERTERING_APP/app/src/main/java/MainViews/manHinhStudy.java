package MainViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.convertering.R;
import StudyManagement.studymanagement_student_Record;
import StudyManagement.studymanagement_student_search;
import StudyManagement.studymanagement_student_uploadvideo;

import view.studymanagement_student_diary;

public class manHinhStudy extends AppCompatActivity {

    //TextView tên app
    TextView txtStudyManament;

    //Button Back
    ImageButton btnBack;

    //4 Button su kien
    Button btnSearch, btnVideo, btnRecord, btnDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_study);
        hideActionBar();
        addControls();
        addEvents();
    }

    private void addEvents() {

        //Bat su kien cua Button Back
        btnBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Bat su kien Button Video
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVideo=new Intent(manHinhStudy.this, studymanagement_student_uploadvideo.class);
                startActivity(intentVideo);
            }
        });

        //Bat su kien Button Record
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRecord=new Intent(manHinhStudy.this, studymanagement_student_Record.class);
                startActivity(intentRecord);
            }
        });

        //Bat su kien Button Search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearch=new Intent(manHinhStudy.this, studymanagement_student_search.class);
                startActivity(intentSearch);
            }
        });

        //Bat su kien Button Diary
        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDiary=new Intent(manHinhStudy.this, studymanagement_student_diary.class);
                startActivity(intentDiary);
            }
        });
    }

    private void addControls() {

        //Khai báo TextView trên app và cài đặt Typeface
        txtStudyManament= this.<TextView>findViewById(R.id.txtStudyManament);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtStudyManament.setTypeface(typeface);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);

        //Khai bao 4 Button su kien
        btnSearch= this.<Button>findViewById(R.id.btnSearch);
        btnVideo= this.<Button>findViewById(R.id.btnVideo);
        btnRecord= this.<Button>findViewById(R.id.btnRecord);
        btnDiary= this.<Button>findViewById(R.id.btnDiary);
    }

    private void hideActionBar() {

    }
}
