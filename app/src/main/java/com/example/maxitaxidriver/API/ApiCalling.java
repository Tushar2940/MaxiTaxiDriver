package com.example.maxitaxidriver.API;

import com.example.maxitaxidriver.LoginResponse;
import com.example.maxitaxidriver.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCalling {
    @POST(ApiConstant.LoginDriver)
    Call<LoginResponse> LoginDriver(@Query("username") String username, @Query("password") String password);

    @POST(ApiConstant.getBooking)
    Call<ResponseModel> getBookingDetail(@Query("DriverID") int DriverID);
}
