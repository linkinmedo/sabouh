package com.example.linkinmedo.datacheck;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by linkinmedo on 12/24/14.
 */
public class DataReceiver extends PhoneStateListener {
    public void onDataConnectionStateChanged(int state){
        switch(state) {
            case TelephonyManager.DATA_DISCONNECTED:
                Log.i("State: ", "Offline");



                break;



               case TelephonyManager.DATA_CONNECTED:
                Log.i("State: ", "Connected");

                break;
        }}


}

