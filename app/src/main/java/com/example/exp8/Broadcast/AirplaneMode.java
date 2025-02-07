package com.example.exp8.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneMode extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            Toast.makeText(context, isAirplaneModeOn ? "Airplane Mode Enabled" : "Airplane Mode Disabled", Toast.LENGTH_SHORT).show();
        }
    }
}
