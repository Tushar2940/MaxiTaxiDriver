package com.example.maxitaxidriver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.maxitaxidriver.API.ApiCalling;
import com.example.maxitaxidriver.SharedPrefrences.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    Context context;
    RecyclerView bookingData_rv;
    BookingDataAdapter bookingDataAdapter;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        context = BookingActivity.this;

        apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        bookingData_rv = findViewById(R.id.bookingData_rv);
        progressDialog = new CustomProgressDialog(context);
        progressDialog.show();

        Call<ResponseModel> call = apiCalling.getBookingDetail(Preferences.getInstance(context).getInt(Preferences.DRIVER_ID));
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful())
                {
                    ResponseModel model = response.body();
                    List<ResponseModel.Responsebody> list = model.getResultData();
                    if (list.size() > 0)
                    {
                        bookingDataAdapter = new BookingDataAdapter(context,list);
                        bookingData_rv.setLayoutManager(new LinearLayoutManager(context));
                        bookingData_rv.setAdapter(bookingDataAdapter);
                        bookingDataAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }else {

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }
}