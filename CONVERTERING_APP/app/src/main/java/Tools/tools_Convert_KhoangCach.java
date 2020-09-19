package Tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.convertering.R;

public class tools_Convert_KhoangCach extends AppCompatActivity {

    //2 spinners để hiển thị các đơn vị có thể đổi
    Spinner spNhap, spDoi;
    String[] arrDistance1;
    ArrayAdapter<String> adapterDistance1;

    String[] arrDistance2;
    ArrayAdapter<String> adapterDistance2;

    //các flag để lưu lại đơn vị cần đo
    int FLAG_1;
    int FLAG_2;

    //Button để back màn hình
    ImageButton btnBack;

    //Button để xác nhận đổi đơn vị
    ImageButton btnChuyenDoi;

    //EditText để nhập vào giá trị cần chuyển đổi
    EditText txtChuyenDoi;

    //TextView hiển thị kết quả sau khi chuyển đổi
    TextView txtKetQua;

    //tạo ra mảng 2 chiều để đổi đơn vị, và tạo giá trị
    double DV[][]={{1      , 0.001, 0.0001, 0.00001 , 0.000001},
            {1000   , 1     , 0.1   , 0.01   , 0.0001},
            {10000  , 10    , 1      , 0.1    , 0.01},
            {100000 , 100   , 10     , 1       , 0.1},
            {1000000, 1000  , 100    , 10      , 1}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools__convert__khoang_cach);
        hideActionBar();
        addControls();
        addEvents();
    }

    private void hideActionBar() {

    }

    private void addEvents() {
        //bắt sự kiện ButtonBack
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                troLaiManHinhChinh();
            }
        });

        //bắt sự kiện trên spinner nhập vào
        spNhap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FLAG_1=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tools_Convert_KhoangCach.this, "Vui lòng chọn đơn vị tiêu chuẩn", Toast.LENGTH_LONG).show();
            }
        });

        //bắt sự kiện trên spinner chuyển đổi
        spDoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FLAG_2=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(tools_Convert_KhoangCach.this, "Vui lòng chọn đơn vị muốn chuyển đổi", Toast.LENGTH_LONG).show();
            }
        });

        //bắt sự kiện khi nhấn vào Button chuyển đổi
        btnChuyenDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanChuyenDoi();
            }
        });
    }

    private void xacNhanChuyenDoi() {
        String str=txtChuyenDoi.getText().toString();
        //int a= Integer.parseInt(txtChuyenDoi.getText().toString());
        if(str.length()==0) {
            Toast.makeText(tools_Convert_KhoangCach.this, "Vui lòng nhập vào số muốn chuyển đổi", Toast.LENGTH_LONG).show();
        }
        else {
            int a= Integer.parseInt(txtChuyenDoi.getText().toString());
            txtKetQua.setText("Kết quả là "+(a/DV[FLAG_1][FLAG_2]));
            txtChuyenDoi.setText("");
        }
    }

    private void troLaiManHinhChinh() {
        finish();
    }

    private void addControls() {
        //khai báo cho spinner nhập phần tử
        spNhap= this.<Spinner>findViewById(R.id.spNhap2);
        arrDistance1=getResources().getStringArray(R.array.distance);
        adapterDistance1=new ArrayAdapter<>(tools_Convert_KhoangCach.this, android.R.layout.simple_dropdown_item_1line, arrDistance1);
        spNhap.setAdapter(adapterDistance1);

        //khai báo cho spinner đổi phần tử
        spDoi= this.<Spinner>findViewById(R.id.spDoi2);
        arrDistance2=getResources().getStringArray(R.array.distance);
        adapterDistance2=new ArrayAdapter<>(tools_Convert_KhoangCach.this, android.R.layout.simple_dropdown_item_1line, arrDistance2);
        spDoi.setAdapter(adapterDistance2);

        //khai báo Button chuyển đổi và giá trị khởi tạo của 2 biến cờ
        btnChuyenDoi= this.<ImageButton>findViewById(R.id.btnChuyenDoi2);
        FLAG_1=-1;
        FLAG_2=-1;

        //khai báo EditText chuyển đổi
        txtChuyenDoi= this.<EditText>findViewById(R.id.txtChuyenDoi2);

        //khai báo TextView hiển thị kết quả
        txtKetQua= this.<TextView>findViewById(R.id.txtKetQua2);

        //khai báo Button để back màn hình
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);

    }
}
