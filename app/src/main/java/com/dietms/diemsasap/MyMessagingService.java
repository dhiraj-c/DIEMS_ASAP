package com.dietms.diemsasap;

import android.os.Handler;
import android.os.Looper;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null){
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MyNotification notification = new MyNotification(getApplicationContext());
                    notification.createNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
                }
            });
        }
    }
}
