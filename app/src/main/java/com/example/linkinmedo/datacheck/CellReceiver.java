package com.example.linkinmedo.datacheck;

import android.content.Context;
import android.telephony.PhoneStateListener;

import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by linkinmedo on 12/27/14.
 */
public class CellReceiver extends PhoneStateListener {

   public void onServiceStateChanged (ServiceState serviceState) {
       switch (serviceState.getState())
       {
           case ServiceState.STATE_OUT_OF_SERVICE:
               Log.i("Cell: ", "Offline");
       }
    }
}
