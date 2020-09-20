package com.ujjwalsingh.carezone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlertReceiver extends BroadcastReceiver {
    static String name;
    static int icon;
    static int id;
    static int rcode;
    @Override
    public void onReceive(Context context, Intent intent) {

        name = intent.getStringExtra("pill_name");
        icon = intent.getIntExtra("icon",0);
        id = intent.getIntExtra("id",0);
        rcode = intent.getIntExtra("rcode",0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"channelID")
                .setContentTitle(name)
                .setContentText("Take your pill!")
                .setSmallIcon(R.drawable.drug5);

        Log.i("Snocker",name);
        Log.i("Snockers",String.valueOf(id));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(rcode,builder.build());
        FirstActivity.noteViewModel.deleteByUserId(id);

//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());
//        Log.i("sperdi","Dfd");
    }
}