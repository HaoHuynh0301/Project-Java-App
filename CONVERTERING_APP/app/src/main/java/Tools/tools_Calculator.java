package Tools;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.convertering.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tools_Calculator extends AppCompatActivity {

    //Khai báo biến result để hiển thị kết quả (Double)
    double result;

    //TextView hiển trị kết quả
    TextView txtKetQua;

    //9 Buttons hiện số
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;

    //4 Button cộng trừ nhân chia
    Button btnCong, btnTru, btnNhan, btnChia;

    //Button Clean all và Clean
    Button btnXoa, btnXoaAll;

    //Button dấu chấm và dấu bằng
    Button btnCham, btnBang;

    int FLAG=-1;

    private boolean isOpPress=false;
    private boolean FLAG_OP=true;
    private double firstNumber=0;
    private int secondNumberIndex=0;
    private char currentOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools__calculator);
        addControls();
        addEvents();
    }

    private void addEvents() {

        //Bắt sự kiện khi nhấn vô Button 0
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo0();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 1
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo1();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 2
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo2();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 4
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo4();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 3
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo3();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 5
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo5();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 6
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo6();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 7
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo7();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 8
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo8();
            }
        });

        //Bắt sự kiện khi nhấn vô Button 9
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anSo9();
            }
        });

        //Bắt sự kiện khi nhấn vô Button cộng
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anNutCong();
            }
        });

        //Bắt sự kiện khi nhấn vô Button trừ
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anNutTru();
            }
        });

        //Bắt sự kiện khi nhấn vô Button nhân
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anNutNhan();
            }
        });

        //Bắt sự kiện khi nhấn vô Button chia
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anNutChia();
            }
        });

        //Băt sự kiện khi bấm vô Button Clean All
        btnXoaAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanAll();
            }
        });

        //Băt sự kiện khi bấm vô Button Clean
        btnXoa.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });

        //Băt sự kiện khi bấm vô Button Chấm
        btnCham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bamButtonCham();
            }
        });

        //Bắt sự kiện khi bấm vô Button Bằng
        btnBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bamButtonBang();
            }
        });
    }

    //************************************************************************************************
    private void bamButtonBang() {
        FLAG_OP=false;
        if(isOpPress) {
            if(currentOP=='+') {
                String contenCurret=txtKetQua.getText().toString();
                String secondNumberScreen=contenCurret.substring(secondNumberIndex, contenCurret.length());
                if(secondNumberScreen.isEmpty()) {
                    txtKetQua.setText(firstNumber+"");
                    isOpPress=false;
                }
                else {
                    double secondNumber=Double.parseDouble(secondNumberScreen);
                    secondNumber+=firstNumber;
                    txtKetQua.setText(String.valueOf(secondNumber));
                    isOpPress=false;

                }
            }
            else if (currentOP=='-') {
                String contenCurret=txtKetQua.getText().toString();
                String secondNumberScreen=contenCurret.substring(secondNumberIndex, contenCurret.length());
                if(secondNumberScreen.isEmpty()) {
                    txtKetQua.setText(firstNumber+"");
                    isOpPress=false;
                }
                else {
                    double secondNumber=Double.parseDouble(secondNumberScreen);
                    firstNumber-=secondNumber;
                    txtKetQua.setText(firstNumber+"");
                    isOpPress=false;
                }
            }
            else if (currentOP=='*') {
                String contenCurret=txtKetQua.getText().toString();
                String secondNumberScreen=contenCurret.substring(secondNumberIndex, contenCurret.length());
                if(secondNumberScreen.isEmpty()) {
                    txtKetQua.setText(firstNumber+"");
                    isOpPress=false;
                }
                else {

                    double secondNumber=Double.parseDouble(secondNumberScreen);
                    secondNumber*=firstNumber;
                    txtKetQua.setText(String.valueOf(secondNumber));
                    isOpPress=false;
                }
            }
            else if (currentOP=='/') {
                String contenCurret=txtKetQua.getText().toString();
                String secondNumberScreen=contenCurret.substring(secondNumberIndex, contenCurret.length());
                if(secondNumberScreen.isEmpty()) {
                    txtKetQua.setText(firstNumber+"");
                    isOpPress=false;
                }
                else {
                    double secondNumber=Double.parseDouble(secondNumberScreen);
                    secondNumber/=firstNumber;
                    secondNumber=1/secondNumber;
                    txtKetQua.setText(String.valueOf(secondNumber));
                    isOpPress=false;
                }
            }
        }
        else {
            Toast.makeText(tools_Calculator.this, "Done !!", Toast.LENGTH_SHORT).show();
        }
    }
    //************************************************************************************************

    private void bamButtonCham() {
        txtKetQua.append(".");
    }

    private void clean() {
        String beforeRemove=txtKetQua.getText().toString();
        if(beforeRemove.length()==0) {
            Toast.makeText(this, "Error!!!", Toast.LENGTH_LONG).show();
        }
        else {
            String afterRemove=delete(txtKetQua.getText().toString());
            txtKetQua.setText(afterRemove);
        }
    }

    private void cleanAll() {
        txtKetQua.setText("");
    }

    private void anNutChia() {
        String screenContent=txtKetQua.getText().toString();
        secondNumberIndex=screenContent.length()+1;
        firstNumber=Double.parseDouble(screenContent);
        txtKetQua.append("/");
        isOpPress=true;
        currentOP='/';
    }

    private void anNutNhan() {
        String screenContent=txtKetQua.getText().toString();
        secondNumberIndex=screenContent.length()+1;
        firstNumber=Double.parseDouble(screenContent);
        txtKetQua.append("*");
        isOpPress=true;
        currentOP='*';
    }

    private void anNutTru() {
        String screenContent=txtKetQua.getText().toString();
        secondNumberIndex=screenContent.length()+1;
        firstNumber=Double.parseDouble(screenContent);
        txtKetQua.append("-");
        isOpPress=true;
        currentOP='-';
    }

    private void anNutCong() {
        String screenContent=txtKetQua.getText().toString();
        secondNumberIndex=screenContent.length()+1;
        firstNumber=Double.parseDouble(screenContent);
        txtKetQua.append("+");
        isOpPress=true;
        currentOP='+';
    }

    private void anSo0() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("0");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("0");
            FLAG_OP=true;
        }
    }

    private void anSo9() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("9");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("9");
            FLAG_OP=true;
        }
    }

    private void anSo8() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("8");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("8");
            FLAG_OP=true;
        }
    }

    private void anSo7() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("7");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("7");
            FLAG_OP=true;
        }
    }

    private void anSo6() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("6");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("6");
            FLAG_OP=true;
        }
    }

    private void anSo5() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("5");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("5");
            FLAG_OP=true;
        }
    }

    private void anSo4() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("4");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("4");
            FLAG_OP=true;
        }
    }

    private void anSo3() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("3");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("3");
            FLAG_OP=true;
        }
    }

    private void anSo2() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("2");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("2");
            FLAG_OP=true;
        }
    }

    private void anSo1() {
        if(isOpPress || FLAG_OP) {
            txtKetQua.append("1");
            FLAG_OP=true;
        }
        else {
            txtKetQua.setText("1");
            FLAG_OP=true;
        }
    }

    private void addControls() {

        //Khai báo 9 Buttons hiện số
        btn0= this.<Button>findViewById(R.id.btn0);
        btn1= this.<Button>findViewById(R.id.btn1);
        btn2= this.<Button>findViewById(R.id.btn2);
        btn3= this.<Button>findViewById(R.id.btn3);
        btn4= this.<Button>findViewById(R.id.btn);
        btn5= this.<Button>findViewById(R.id.btn5);
        btn6= this.<Button>findViewById(R.id.btn6);
        btn7= this.<Button>findViewById(R.id.btn7);
        btn8= this.<Button>findViewById(R.id.btn8);
        btn9= this.<Button>findViewById(R.id.btn9);

        //Khai báo TextView kết quả
        txtKetQua= this.<TextView>findViewById(R.id.txtKetQua);

        //Khai báo 4 Buttons cộng trừ nhân chia
        btnCong= this.<Button>findViewById(R.id.btnCong);
        btnTru= this.<Button>findViewById(R.id.btnTru);
        btnNhan= this.<Button>findViewById(R.id.btnNhan);
        btnChia= this.<Button>findViewById(R.id.btnChia);

        //Khai báo Button Clean All và Clean
        btnXoa= this.<Button>findViewById(R.id.btnXoa);
        btnXoaAll= this.<Button>findViewById(R.id.btnXoaAll);

        //Khai báo Button Chấm và Button Bằng
        btnCham= this.<Button>findViewById(R.id.btnCham);
        btnBang= this.<Button>findViewById(R.id.btnBang);

        //Khai báo cho biến reuslt
        result=-1;

    }

    //Hàm để xóa 1 phần tử
    public String delete(String string) {
        int lenght= string.length();
        String tem=string.substring(0, lenght-1);
        return tem;
    }

}
