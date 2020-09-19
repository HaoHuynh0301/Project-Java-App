package StudyManagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.sinhVien;

import com.example.convertering.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studymanagement_teacher_themSinhVien extends AppCompatActivity {

    //TextView Title
    TextView txtThemVaoSinhVien;

    //4 EditText them vao
    EditText txtMSSV, txtHoVaTen, txtSoDienThoai, txtEmail;

    //3 Buttons Back, Huy va Xac Nhan
    Button btnHuy, btnBack, btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement_teacher_them_sinh_vien);
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
                txtMSSV.setText("");
                txtHoVaTen.setText("");
                txtEmail.setText("");
                txtSoDienThoai.setText("");
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

                sinhVien sinhVien=new sinhVien(mssv, hoten, email, sodienthoai);
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    DatabaseReference myRef=database.getReference("DSSinhVien");
                String id=myRef.push().getKey();
                myRef.child(id).setValue(sinhVien).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(studymanagement_teacher_themSinhVien.this, "Success!!!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(studymanagement_teacher_themSinhVien.this, "Error!!!", Toast.LENGTH_LONG).show();
                    }
                });
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


    }
}
