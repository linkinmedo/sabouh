package com.example.linkinmedo.datacheck;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by linkinmedo on 12/24/14.
 */
public class DataReceiver extends PhoneStateListener {
    Context context;

    public DataReceiver(Context context) {
        super();
        // TODO Auto-generated constructor stub
        this.context=context;
    }
     public void onDataConnectionStateChanged(int state){
        switch(state) {
            case TelephonyManager.DATA_DISCONNECTED:
                Log.i("State: ", "Offline");
                long when = System.currentTimeMillis();
                NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent=new Intent(context,MainActivity.class);
                PendingIntent  pendingIntent=PendingIntent.getActivity(context, 0, intent, 0);
                Notification notification;

                int icon = R.drawable.notification_icon;
                notification = new Notification.Builder(context)
                        .setContentTitle("Mobile data connection lost")
                        .setContentText(
                                "touch to open app").setSmallIcon(icon)
                        .setContentIntent(pendingIntent).setWhen(when).setAutoCancel(true)
                        .build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notification.defaults |= Notification.DEFAULT_SOUND;
                nm.notify(0, notification);



                break;



               case TelephonyManager.DATA_CONNECTED:
                Log.i("State: ", "Connected");

                break;
        }}


}

