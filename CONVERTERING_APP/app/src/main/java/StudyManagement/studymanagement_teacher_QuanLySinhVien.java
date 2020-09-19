package StudyManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.convertering.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.sinhVien;

import java.util.ArrayList;

public class studymanagement_teacher_QuanLySinhVien extends AppCompatActivity {

    //Khai bao TextView Title
    TextView txtQuanLySinhVien;

    //Button Back
    ImageButton btnBack;

    //ListView Sinh Vien
    ListView lvSinhVien;
    ArrayList<sinhVien> arrSinhVien;
    sinhVienAdapter adapterSinhVien;

    //ToolBar
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement_teacher__quan_ly_sinh_vien);
        addControls();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_listview_sinhvien_2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.them_vao_sinhvien_2) {
            Intent intentThemSinhVien=new Intent(studymanagement_teacher_QuanLySinhVien.this, studymanagement_teacher_themSinhVien.class);
            startActivity(intentThemSinhVien);
            return true;
        }

        return false;
    }

    private void addEvents() {

        //Bat su kien Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {

        //Khai bao TextView Title va cai dat Typeface
        txtQuanLySinhVien= this.<TextView>findViewById(R.id.txtQuanLySinhVien);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtQuanLySinhVien.setTypeface(typeface);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);
        //Khai bai ListView SinhVien, Arr va adapter
        lvSinhVien= this.<ListView>findViewById(R.id.lvSInhVien);
        arrSinhVien=new ArrayList<>();
        getData();
        //arrSinhVien.add(new sinhVien("1", "1809687", "Hao Huynh", "hao152903@gmail.com", "0932843656"));
        //arrSinhVien.add(new sinhVien("2", "1809688", "Le Thu", "thu0902@gmail.com", "0939349037"));
        adapterSinhVien=new sinhVienAdapter(this, R.layout.custum_layout_forstudydent_listview, arrSinhVien);
        lvSinhVien.setAdapter(adapterSinhVien);

        //Khai bao ToolBar
        toolbar= this.<Toolbar>findViewById(R.id.myToolbar);
        toolbar.setTitle("Quản lý sinh viên");
        setSupportActionBar(toolbar);

    }

    private void getData() {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("DSSinhVien");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapterSinhVien.clear();
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    sinhVien sinhVien=data.getValue(model.sinhVien.class);
                    if(sinhVien!=null) {
                        sinhVien.setId(data.getKey());
                        adapterSinhVien.add(sinhVien);
                    }
                }
                Toast.makeText(studymanagement_teacher_QuanLySinhVien.this, "Thêm dữ liệu thành công !!!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(studymanagement_teacher_QuanLySinhVien.this, "Error!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
