package com.dietms.diemsasap;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyNotification {

    public static final String  CHANNEL_ID = "LEAVE_APP";
    public static final String CHANNEL_NAME = "LEAVE_APP";
    public static final String CHANNEL_DESC = "LEAVE_APP_NOTIFICATIONS";

    Context context;

    public MyNotification(Context context) {
        this.context = context;
    }

    public void createNotification(String title, String text){

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent resultIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESC);

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                .setContentIntent(resultIntent)
                .setAutoCancel(true)
                .setColor(context.getResources().getColor(R.color.colorPrimary));

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(1, builder.build());
    }
}
