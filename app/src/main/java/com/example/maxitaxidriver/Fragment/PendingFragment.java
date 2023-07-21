package com.example.maxitaxidriver.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.maxitaxidriver.API.ApiCalling;
import com.example.maxitaxidriver.Adapter.BookingDataAdapter;
import com.example.maxitaxidriver.CustomProgressDialog;
import com.example.maxitaxidriver.MyApplication;
import com.example.maxitaxidriver.R;
import com.example.maxitaxidriver.Model.ResponseModel;
import com.example.maxitaxidriver.SharedPrefrences.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingFragment extends Fragment {

    Context context;
    RecyclerView bookingData_rv;
    BookingDataAdapter bookingDataAdapter;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;
    TextView nodatatxt;
    SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pendingdata_layout, container, false);

        context = getActivity();

        apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        bookingData_rv = view.findViewById(R.id.bookingData_rv);
        nodatatxt = view.findViewById(R.id.nodatatxt);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        progressDialog = new CustomProgressDialog(context);
        progressDialog.show();
        progressDialog.setCancelable(false);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast toast = Toast.makeText(context, "Please wait! ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                getData();
            }
        });
        getData();

        return view;
    }

    private void getData() {
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
//                        Collections.sort(list, new Comparator<ResponseModel.Responsebody>() {
//                            @Override
//                            public int compare(ResponseModel.Responsebody o1, ResponseModel.Responsebody o2) {
//                                return Long.valueOf(o2.getId()).compareTo(Long.valueOf(o1.getId()));
//                            }
//                        });
                        Preferences.getInstance(context).setBoolean(Preferences.KEY_LOGIN,true);
                        bookingDataAdapter = new BookingDataAdapter(context,list);
                        bookingData_rv.setLayoutManager(new LinearLayoutManager(context));
                        bookingData_rv.setAdapter(bookingDataAdapter);
                        bookingDataAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                        swipeContainer.setRefreshing(false);
                    }else {
                        swipeContainer.setRefreshing(false);
                        progressDialog.dismiss();
                        bookingData_rv.setVisibility(View.GONE);
                        nodatatxt.setVisibility(View.VISIBLE);
                        Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                swipeContainer.setRefreshing(false);
                progressDialog.dismiss();
                bookingData_rv.setVisibility(View.GONE);
                nodatatxt.setVisibility(View.VISIBLE);
                Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
