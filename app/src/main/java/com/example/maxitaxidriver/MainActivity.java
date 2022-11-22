package com.example.maxitaxidriver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxitaxidriver.API.ApiCalling;
import com.example.maxitaxidriver.SharedPrefrences.Preferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btn_submit;
    EditText userName,password;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.LogoColor, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.LogoColor));
        }
        btn_submit = findViewById(R.id.btn_submit);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        progressDialog = new CustomProgressDialog(MainActivity.this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    return;
                                }
                                // Get new FCM registration token
                                token = task.getResult();
                                Preferences.getInstance(MainActivity.this).setString(Preferences.TOKEN,token);
//                                Toast.makeText(MainActivity.this, ""+token, Toast.LENGTH_SHORT).show();
//                                Log.d("Token Push", token);

                                Call<LoginResponse> call = apiCalling.LoginDriver(userName.getText().toString(),
                                                                                  password.getText().toString(),
                                                                                  Preferences.getInstance(MainActivity.this).getString(Preferences.TOKEN));
                                call.enqueue(new Callback<LoginResponse>() {
                                    @Override
                                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                        if (response.isSuccessful())
                                        {
                                            LoginResponse model = response.body();
                                            if (model.isResultStatus())
                                            {
                                                progressDialog.dismiss();
                                                Preferences.getInstance(MainActivity.this).setBoolean(Preferences.KEY_LOGIN,true);
                                                LoginResponse.Responsebody responsebody = model.ResultData;
                                                Preferences.getInstance(MainActivity.this).setInt(Preferences.DRIVER_ID,responsebody.getDriverID());
                                                Toast.makeText(MainActivity.this, model.getResultMessage().toString(), Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this,BookingActivity.class));
                                            }else {
                                                progressDialog.dismiss();
                                                Toast.makeText(MainActivity.this, model.getResultMessage().toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        }else {
                                            progressDialog.dismiss();
                                            Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                                        progressDialog.dismiss();
                                        Toast.makeText(MainActivity.this, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });


            }
        });


    }

}
