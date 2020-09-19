package StudyManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.convertering.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.sinhVien;

public class studymanagement_teacher_suaSinhVien extends AppCompatActivity {

    //TextView Title
    TextView txtThemVaoSinhVien;

    //4 EditText them vao
    EditText txtMSSV, txtHoVaTen, txtSoDienThoai, txtEmail;

    //3 Buttons Back, Huy va Xac Nhan
    Button btnHuy, btnBack, btnXacNhan;

    private sinhVien sinhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement_teacher_sua_sinh_vien);
        addControls();
        addEvents();
    }

    private void addEvents() {
        //Bat su kien Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Bat su kien Button Huy
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sinhVien!=null) {
                    txtSoDienThoai.setText(sinhVien.getSoDienThoai()+"");
                    txtMSSV.setText(sinhVien.getMSSV()+"");
                    txtHoVaTen.setText(sinhVien.getHoTen());
                    txtEmail.setText(sinhVien.getEmail());
                }
                else {
                    Toast.makeText(studymanagement_teacher_suaSinhVien.this, "Error!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Bat su kien Button Xac Nhan
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv=txtMSSV.getText().toString();
                String hoten=txtHoVaTen.getText().toString();
                String email=txtEmail.getText().toString();
                String sodienthoai=txtSoDienThoai.getText().toString();
                String id=sinhVien.getId();

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("DSSinhVien");

                myRef.child(id).child("HoTen").setValue(hoten);
                myRef.child(id).child("MSSV").setValue(mssv);
                myRef.child(id).child("email").setValue(email);
                myRef.child(id).child("soDienThoai").setValue(sodienthoai);

                Toast.makeText(studymanagement_teacher_suaSinhVien.this, "Done!!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void addControls() {
        //Khai bao TextView Title va cai dat Typeface
        txtThemVaoSinhVien= this.<TextView>findViewById(R.id.txtThemVaoSinhVien);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtThemVaoSinhVien.setTypeface(typeface);

        //Khai bao 4 EditText them vao
        txtMSSV= this.<EditText>findViewById(R.id.txtMSSV);
        txtHoVaTen= this.<EditText>findViewById(R.id.txtHoVaTen);
        txtEmail= this.<EditText>findViewById(R.id.txtEmail);
        txtSoDienThoai= this.<EditText>findViewById(R.id.txtSoDienThoai);

        //Khai bao 3 Buttons Back, Huy va Xac Nhan
        btnBack= this.<Button>findViewById(R.id.btnBack);
        btnHuy= this.<Button>findViewById(R.id.btnHuy);
        btnXacNhan= this.<Button>findViewById(R.id.btnXacNhan);

        //get Intent
        Intent intent=getIntent();
        sinhVien= (model.sinhVien) intent.getSerializableExtra("sinhVien");
        if(sinhVien!=null) {
            txtSoDienThoai.setText(sinhVien.getSoDienThoai()+"");
            txtMSSV.setText(sinhVien.getMSSV()+"");
            txtHoVaTen.setText(sinhVien.getHoTen());
            txtEmail.setText(sinhVien.getEmail());
        }
        else {
            Toast.makeText(studymanagement_teacher_suaSinhVien.this, "Error!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
