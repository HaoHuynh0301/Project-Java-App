package StudyManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.convertering.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

import model.sinhVien;

public class sinhVienAdapter extends ArrayAdapter<sinhVien> {

    @NonNull private Activity activity;
    private int resource;
    @NonNull private List<sinhVien> objects;

    public sinhVienAdapter(@NonNull Activity activity, int resource, @NonNull List<sinhVien> objects) {
        super(activity, resource, objects);
        this.activity=activity;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.activity.getLayoutInflater();
        final View view=inflater.inflate(this.resource, null);

        TextView txtHoVaTen= view.<TextView>findViewById(R.id.txtHoVaTen);
        TextView txtMSSV= view.<TextView>findViewById(R.id.txtMSSV);

        final sinhVien sinhVien=this.objects.get(position);
        txtHoVaTen.setText(sinhVien.getHoTen());
        txtMSSV.setText(sinhVien.getMSSV());

        ImageView btnMenu= view.<ImageView>findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(activity, view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.sua_thongtin_sinhvien) {
                            Intent intentSuaSinhVien=new Intent(activity, studymanagement_teacher_suaSinhVien.class);
                            intentSuaSinhVien.putExtra("sinhVien", sinhVien);
                            activity.startActivity(intentSuaSinhVien);
                        }
                        else if(item.getItemId()==R.id.xoa_sinh_vien) {
                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference myRef=database.getReference("DSSinhVien");
                            myRef.child(sinhVien.getId()).removeValue(new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    Toast.makeText(activity, "Done!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return false;
                    }
                });

                popupMenu.getMenuInflater().inflate(R.menu.menu_listview_sinhvien, popupMenu.getMenu());
                popupMenu.show();
            }
        });

        return view;
    }
}
