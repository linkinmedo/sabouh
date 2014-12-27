package com.example.linkinmedo.datacheck;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
Switch aSwitch,bSwitch;
TextView view1,view2;
   public static final DataReceiver dataReceiver = new DataReceiver();
   public static final CellReceiver cellReceiver = new CellReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = (Switch) findViewById(R.id.switch1);
        view1 = (TextView)findViewById(R.id.textView1);
        bSwitch = (Switch) findViewById(R.id.switch2);
        view2 = (TextView)findViewById(R.id.textView2);

        final PackageManager packageManager = getPackageManager();
        final ComponentName componentName = new ComponentName(getApplicationContext(),BootReceiver.class);
        final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){

                telephonyManager.listen(dataReceiver,
                        PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
                packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

            }else {
                packageManager.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
                telephonyManager.listen(dataReceiver,
                        PhoneStateListener.LISTEN_NONE);


            }
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    telephonyManager.listen(cellReceiver,
                            PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
                    packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

                }else {
                    packageManager.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
                    telephonyManager.listen(cellReceiver,
                            PhoneStateListener.LISTEN_NONE);


                }
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
