package com.example.linkinmedo.datacheck;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;

import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by linkinmedo on 12/27/14.
 */
public class CellReceiver extends PhoneStateListener {
    Context context;

    public CellReceiver(Context context) {
        super();
        // TODO Auto-generated constructor stub
        this.context=context;
    }

   public void onServiceStateChanged (ServiceState serviceState) {

       switch (serviceState.getState())
       {
           case ServiceState.STATE_OUT_OF_SERVICE:
               Log.i("Cell: ", "Offline");
               long when = System.currentTimeMillis();
               NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
               Intent intent=new Intent(context,MainActivity.class);
               PendingIntent pendingIntent=PendingIntent.getActivity(context, 0, intent, 0);
               Notification notification;

               int icon = R.drawable.notification_icon;
               notification = new Notification.Builder(context)
                       .setContentTitle("Connection to cell lost")
                       .setContentText(
                               "touch to open app").setSmallIcon(icon)
                       .setContentIntent(pendingIntent).setWhen(when).setAutoCancel(true)
                       .build();
               notification.flags |= Notification.FLAG_AUTO_CANCEL;
               notification.defaults |= Notification.DEFAULT_SOUND;
               nm.notify(0, notification);
       }
    }
}
