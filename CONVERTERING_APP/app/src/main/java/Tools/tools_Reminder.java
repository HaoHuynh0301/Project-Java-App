package Tools;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.convertering.R;

import java.util.Calendar;

public class tools_Reminder extends AppCompatActivity {

    //Notification id
    private int notificationID=-1;

    //2 Button Set vs Cancel
    Button btnSet, btnCancel;

    //TimePicker
    TimePicker timePicker;

    //EditText CurrentTime
    EditText txtCurrentTime;

    //Button Back
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools__reminder);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhSetting();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moManHinhSettingCancel();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void moManHinhSettingCancel() {
        Intent intentSetting=new Intent(tools_Reminder.this, tools_Reminder_Setting.class);
        intentSetting.putExtra("notificationID", notificationID);
        intentSetting.putExtra("toDo", txtCurrentTime.getText().toString());
        //startActivity(intentSetting);

        PendingIntent alarmIntent=PendingIntent.getBroadcast(tools_Reminder.this, 0, intentSetting, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm= (AlarmManager) getSystemService(ALARM_SERVICE);

        alarm.cancel(alarmIntent);
        Toast.makeText(tools_Reminder.this, "Xóa thành công", Toast.LENGTH_LONG).show();
    }

    private void moManHinhSetting() {
        Intent intentSetting=new Intent(tools_Reminder.this, tools_Reminder_Setting.class);
        intentSetting.putExtra("notificationID", notificationID);
        intentSetting.putExtra("toDo", txtCurrentTime.getText().toString());
        //startActivity(intentSetting);

        PendingIntent alarmIntent=PendingIntent.getBroadcast(tools_Reminder.this, 0, intentSetting, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm= (AlarmManager) getSystemService(ALARM_SERVICE);

        int hour=timePicker.getCurrentHour();
        int minute=timePicker.getCurrentMinute();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        long alarmStartTime=calendar.getTimeInMillis();

        alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
        Toast.makeText(tools_Reminder.this, "Tạo thành công", Toast.LENGTH_SHORT).show();

    }

    private void addControls() {

        //Khai báo 2 Button Set vs Cancel
        btnSet= this.<Button>findViewById(R.id.btnSet);
        btnCancel= this.<Button>findViewById(R.id.btnCancel);

        //Khai báo TimePicker
        timePicker= this.<TimePicker>findViewById(R.id.timePicker);

        //Khai báo EditText CurrentTime
        txtCurrentTime= this.<EditText>findViewById(R.id.txtTimeCurrent);

        //Khai bao Button Back
        btnBack= this.<ImageButton>findViewById(R.id.btnBack);
    }

}
