package com.example.exp8;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exp8.Broadcast.AirplaneMode;
import com.example.exp8.Broadcast.BatteryLevel;
import com.example.exp8.Broadcast.ConnectivityReceiver;

public class MainActivity extends AppCompatActivity {

    private final AirplaneMode airplaneMode = new AirplaneMode();
    private final ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver();
    private final BatteryLevel batteryLevel = new BatteryLevel();

    private Switch airplaneModeSwitch, connectivitySwitch, batteryLevelSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneModeSwitch = findViewById(R.id.switch2);
        connectivitySwitch = findViewById(R.id.switch4);
        batteryLevelSwitch = findViewById(R.id.switch3);

        // Connectivity Switch
        connectivitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                registerReceiver(connectivityReceiver, filter);
            } else {
                unregisterReceiver(connectivityReceiver);
            }
        });

        // Airplane Mode Switch
        airplaneModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                registerReceiver(airplaneMode, filter);
            } else {
                unregisterReceiver(airplaneMode);
            }
        });

        // Battery Level Switch
        batteryLevelSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                registerReceiver(batteryLevel, filter);
            } else {
                unregisterReceiver(batteryLevel);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(airplaneMode);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            unregisterReceiver(connectivityReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            unregisterReceiver(batteryLevel);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
