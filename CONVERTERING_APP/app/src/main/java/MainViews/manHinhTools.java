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
import Tools.tools_Calculator;
import Tools.tools_Convert;
import Tools.tools_Reminder;
import Tools.toolss_Note;

public class manHinhTools extends AppCompatActivity {

    //4 Button để mở Calculator, Convert, Note, Reminder
    Button btnCal, btnNote, btnReminder, btnConvert;

    //Buton back
    ImageButton btnBack;

    //TextView Tools
    TextView txtTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tools);
        hideActionBar();
        addControls();
        addEvents();
    }

    private void addEvents() {

        //Bắt sự kiện btnCal
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhCal();
            }
        });

        //Bắt sự kiện btnNote
        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhNote();
            }
        });

        //Bắt sự kiện btnConvert
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhConvert();
            }
        });

        //Bắt sự kiện btnReminder
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhReminder();
            }
        });

        //Bắt sự kiện trên Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void moManHinhReminder() {
        Intent intentReminder=new Intent(manHinhTools.this, tools_Reminder.class);
        startActivity(intentReminder);
    }

    private void moManHinhConvert() {
        Intent intentConvert=new Intent(manHinhTools.this, tools_Convert.class);
        startActivity(intentConvert);
    }

    private void moManHinhNote() {
       Intent intentNote=new Intent(manHinhTools.this, toolss_Note.class);
       startActivity(intentNote);
    }

    private void moManHinhCal() {
        Intent intentCal=new Intent(manHinhTools.this, tools_Calculator.class);
        startActivity(intentCal);

    }

    private void addControls() {

    }

    private void hideActionBar() {

        //Khai báo 4 Button để mở Calculator, Convert, Note, Reminder
        btnConvert= this.<Button>findViewById(R.id.btnConvert);
        btnNote= this.<Button>findViewById(R.id.btnNote);
        btnReminder= this.<Button>findViewById(R.id.btnReminder);
        btnCal= this.<Button>findViewById(R.id.btnCal);

        //Khai báo Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);

        //Khai báo TextView Tools và cài đặt Interface
        txtTools= this.<TextView>findViewById(R.id.txtTools);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtTools.setTypeface(typeface);
    }
}
