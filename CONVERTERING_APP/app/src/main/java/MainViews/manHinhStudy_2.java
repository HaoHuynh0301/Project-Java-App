package MainViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import StudyManagement.studymanagement_teacher_QuanLySinhVien;

import com.example.convertering.R;

public class manHinhStudy_2 extends AppCompatActivity {

    //TextView Title
    TextView txtStudyManament, txtForTeacher;

    //3 MainButton
    Button btnQuanLySinhVien;

    //Button Back
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_study_2);
        addControls();
        addEvents();
    }

    private void addEvents() {

        //Bat su kien btnQuanLySinhVien
        btnQuanLySinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentQuanLySinhVien=new Intent(manHinhStudy_2.this, studymanagement_teacher_QuanLySinhVien.class);
                startActivity(intentQuanLySinhVien);
            }
        });

        //Bat su kien Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {

        //Khai bao va cai dat Typeface cho 2 title
        txtStudyManament= this.<TextView>findViewById(R.id.txtStudyManament);
        txtForTeacher= this.<TextView>findViewById(R.id.txtTeacher);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtStudyManament.setTypeface(typeface);
        txtForTeacher.setTypeface(typeface);

        //Khai bao 3 Main Button
        btnQuanLySinhVien= this.<Button>findViewById(R.id.btnQuanLySinhVien);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);
    }
}
