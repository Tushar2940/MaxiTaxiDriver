package com.example.maxitaxidriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.maxitaxidriver.SharedPrefrences.Preferences;

public class SplashActivity extends AppCompatActivity {

    Context context;
    TextView versiontxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = SplashActivity.this;
        versiontxt = findViewById(R.id.versiontxt);

        Thread timer = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1000);
                    if (Preferences.getInstance(context).getBoolean(Preferences.KEY_LOGIN)) {
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = pInfo.versionName;
            versiontxt.setText("Version : "+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}