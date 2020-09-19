package Tools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.convertering.R;

public class tools_Convert extends AppCompatActivity {
    ImageButton btnThoat, btnDistance, btnSpeed, btnWeigth, btnTem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools__convert);
        hideActionBar();
        addControls();
        addEvents();
    }

    private void hideActionBar() {

    }

    private void addEvents() {

        //Bắt sự kiện Button thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Bắt sự kiện Button mở màn hình Khoảng cách
        btnDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhKhoangCach();
            }
        });

        //Bắt sự kiện Button mở màn hình Nhiệt độ
        btnTem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhNhietDo();
            }
        });

        //Bắt sự kiện Button mở màn hình Khối lượng
        btnWeigth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhKhoiLuong();
            }
        });

        //Bắt sự kiên Button mở màn hình Tốc độ
        btnSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhTocDo();
            }
        });
    }

    private void moManHinhTocDo() {
        Intent intentSpeed=new Intent(tools_Convert.this, tools_Convert_TocDo.class);
        startActivity(intentSpeed);

    }

    private void moManHinhKhoiLuong() {
        Intent intentWeight=new Intent(tools_Convert.this, tools_Convert_KhoiLuong.class);
        startActivity(intentWeight);
    }

    private void moManHinhNhietDo() {
        Intent intentTemp=new Intent(tools_Convert.this, tools_Convert_NhietDo.class);
        startActivity(intentTemp);
    }

    private void moManHinhKhoangCach() {
        Intent intentDistance=new Intent(tools_Convert.this, tools_Convert_KhoangCach.class);
        startActivity(intentDistance);
    }

    private void addControls() {

        //khai báo các nút để convert
        btnDistance= this.<ImageButton>findViewById(R.id.btnDistance);
        btnWeigth= this.<ImageButton>findViewById(R.id.btnWeight);
        btnSpeed= this.<ImageButton>findViewById(R.id.btnSpeed);
        btnTem= this.<ImageButton>findViewById(R.id.btnTem);

        //Khai báo Button để back màn hình
        btnThoat= this.<ImageButton>findViewById(R.id.btnThoat);
    }
}
