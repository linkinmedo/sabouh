package com.example.linkinmedo.datacheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * Created by linkinmedo on 12/24/14.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(MainActivity.dataReceiver,
                PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
    }
}
