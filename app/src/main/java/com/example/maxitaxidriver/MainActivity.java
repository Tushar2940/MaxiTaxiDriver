package com.example.maxitaxidriver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maxitaxidriver.API.ApiCalling;
import com.example.maxitaxidriver.SharedPrefrences.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btn_submit;
    EditText userName,password;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_submit = findViewById(R.id.btn_submit);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        progressDialog = new CustomProgressDialog(MainActivity.this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Call<LoginResponse> call = apiCalling.LoginDriver(userName.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful())
                        {
                            LoginResponse model = response.body();
                            if (model.isResultStatus())
                            {
                                progressDialog.dismiss();
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
}