package Tools;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.convertering.R;

public class tools_Reminder_Setting extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationID=intent.getIntExtra("notificationID", 0);
        String messege=intent.getStringExtra("todo");

        Intent mainIntent=new Intent(context, tools_Reminder.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context, 0, mainIntent, 0);
        NotificationManager mnotificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.warnignicon32)
                .setContentTitle("It's time")
                .setContentText(messege)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_DEFAULT);
        mnotificationManager.notify(notificationID, builder.build() );
    }
}
