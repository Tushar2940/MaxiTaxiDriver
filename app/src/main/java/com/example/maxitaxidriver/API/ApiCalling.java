package com.example.maxitaxidriver.API;

import com.example.maxitaxidriver.LoginResponse;
import com.example.maxitaxidriver.Model.Response;
import com.example.maxitaxidriver.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCalling {
    @POST(ApiConstant.LoginDriver)
    Call<LoginResponse> LoginDriver(@Query("username") String username,
                                    @Query("password") String password,
                                    @Query("token") String token);

    @POST(ApiConstant.getBooking)
    Call<ResponseModel> getBookingDetail(@Query("DriverID") int DriverID);


    @POST(ApiConstant.DriverCancel)
    Call<Response> driverCancel(@Query("bookingId") int bookingId,
                                @Query("reason") String reason,
                                @Query("DriverID") int DriverID);

    @POST(ApiConstant.saveStatus)
    Call<Response> saveStatus(@Query("bookingId") int bookingId);

    @POST(ApiConstant.getCompleteBookingByDriver)
    Call<ResponseModel> getCompleteBookingDetail(@Query("DriverID") int DriverID);
}
