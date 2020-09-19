package MainViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.convertering.R;

public class MainActivity extends AppCompatActivity {

    //TextView Convertering
    TextView txtConvertering;

    //3 Button Tools, Study, Work
    Button btnTools, btnSutdy, btnWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideActionBar();
        addControls();
        addEvents();

    }

    private void addEvents() {

        //Bat su kien Button Tools
        btnTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTools=new Intent(MainActivity.this, manHinhTools.class);
                startActivity(intentTools);
            }
        });

        //Bat su kien Button Study
        btnSutdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStudy=new Intent(MainActivity.this, studymanagement_Login.class);
                startActivity(intentStudy);
            }
        });

        //Bat su kien Button Work
        btnWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWork=new Intent(MainActivity.this, manHinhWork.class);
                startActivity(intentWork);
            }
        });
    }

    private void addControls() {

        //Khai bao TextView Convertering va cai dat Typeface
        txtConvertering= this.<TextView>findViewById(R.id.txtConverterring);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtConvertering.setTypeface(typeface);

        //Khai bao 3 Button Tools, Study, Work
        btnSutdy= this.<Button>findViewById(R.id.btnStudy);
        btnTools= this.<Button>findViewById(R.id.btnTools);
        btnWork= this.<Button>findViewById(R.id.btnWork);

    }

    private void hideActionBar() {

    }
}
