package StudyManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.convertering.R;

public class studymanagement_student_search extends AppCompatActivity {

    SearchView searchView;

    //ListView va Adapter
    ListView lvTimKiem;
    String arrTimKiem[];
    ArrayAdapter<String> adapterTimKiem;

    //Button Back
    ImageButton btnBack;

    //TextView Searching
    TextView txtSearching;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymanagement_student_search);
        addControls();
        addEvents();

    }

    private void addEvents() {

        //Bat su kien SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTimKiem.getFilter().filter(newText);
                return false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {

        //Khai bao SearchView
        searchView= this.<SearchView>findViewById(R.id.searchView);

        //Khai bao ListView va Adapter
        lvTimKiem= this.<ListView>findViewById(R.id.lvTimKiem);
        arrTimKiem=getResources().getStringArray(R.array.distance);
        adapterTimKiem=new ArrayAdapter<>(studymanagement_student_search.this, android.R.layout.simple_dropdown_item_1line, arrTimKiem);
        lvTimKiem.setAdapter(adapterTimKiem);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);

        //Khai bao va cai dat Interface TextView Searching
        txtSearching= this.<TextView>findViewById(R.id.txtSearching);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/Nunito-Black.ttf");
        txtSearching.setTypeface(typeface);

    }
}
