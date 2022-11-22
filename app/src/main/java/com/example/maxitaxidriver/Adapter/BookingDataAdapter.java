package com.example.maxitaxidriver.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxitaxidriver.API.ApiCalling;
import com.example.maxitaxidriver.CustomProgressDialog;
import com.example.maxitaxidriver.MyApplication;
import com.example.maxitaxidriver.R;
import com.example.maxitaxidriver.Model.ResponseModel;
import com.example.maxitaxidriver.SharedPrefrences.Preferences;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDataAdapter extends RecyclerView.Adapter<BookingDataAdapter.ViewHolder> {

    Context context;
    List<ResponseModel.Responsebody> list;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;

    public BookingDataAdapter(Context context, List<ResponseModel.Responsebody> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookingDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_data_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingDataAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bookingNo.setText("" + list.get(position).getContactNo());

        holder.email.setText("" + list.get(position).getEmail());
        holder.date.setText("" + list.get(position).getDate());
        holder.name.setText("" + list.get(position).getName());
        holder.pickup.setText("" + list.get(position).getPick_Up_Address());
        holder.dropoff.setText("" + list.get(position).getDrop_Off_Address());
        holder.totalPassager.setText("" + list.get(position).getNo_off_passanger());
        holder.price.setText("" + list.get(position).getPrice());
        holder.totalDistance.setText("" + list.get(position).getTotalDistance());
        holder.totalTime.setText("" + list.get(position).getTime_to_destination());
        holder.flightNo.setText("" + list.get(position).getFlightno());
        holder.discription.setText("" + list.get(position).getRemarks());
        holder.pickupTime.setText("" + list.get(position).getTime());

        if (list.get(position).getStatus().equals("1")) {
            holder.status.setText("Pending");
        } else if (list.get(position).getStatus().equals("2")) {
            holder.status.setText("Pending");
        } else if (list.get(position).getStatus().equals("3")) {
            holder.status.setText("Not Confirm");
        } else {
            holder.status.setText("Complete");
        }

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                View view1 = LayoutInflater.from(context).inflate(
                        R.layout.layout_warning_dailog,
                        (ConstraintLayout) view.findViewById(R.id.layoutDialogContainer)
                );
                builder.setView(view1);
                ((TextView) view1.findViewById(R.id.textTitle)).setText("Warning");
                ((TextView) view1.findViewById(R.id.textMessage)).setText("Are You sure this Booking is Done.");
                ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes");
                ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                ((ImageView) view1.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

                final AlertDialog alertDialog = builder.create();

                view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        progressDialog.show();
                        Call<com.example.maxitaxidriver.Model.Response> call = apiCalling.saveStatus(list.get(position).getId());
                        call.enqueue(new Callback<com.example.maxitaxidriver.Model.Response>() {
                            @Override
                            public void onResponse(Call<com.example.maxitaxidriver.Model.Response> call, retrofit2.Response<com.example.maxitaxidriver.Model.Response> response) {
                                if (response.isSuccessful()) {
                                    com.example.maxitaxidriver.Model.Response model = response.body();
                                    if (model.isStatus()) {
                                        progressDialog.dismiss();
                                        list.remove(position);
                                        notifyItemChanged(position);
                                        Toast.makeText(context, "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(context, "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<com.example.maxitaxidriver.Model.Response> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();

                    }
                });

                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();

            }
        });

        holder.btnNotDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.remarkpopup);

                EditText reasone = dialog.findViewById(R.id.reasone);
                Button btn_proceed = dialog.findViewById(R.id.btn_proceed);

                btn_proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (reasone.getText().toString().trim().length() == 0) {
                            Toast.makeText(context, "Please Enter Reason", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.show();
                            Call<com.example.maxitaxidriver.Model.Response> call = apiCalling.driverCancel(list.get(position).getId(),
                                    reasone.getText().toString().trim(), Preferences.getInstance(context).getInt(Preferences.DRIVER_ID));
                            call.enqueue(new Callback<com.example.maxitaxidriver.Model.Response>() {
                                @Override
                                public void onResponse(Call<com.example.maxitaxidriver.Model.Response> call, Response<com.example.maxitaxidriver.Model.Response> response) {
                                    if (response.isSuccessful()) {
                                        com.example.maxitaxidriver.Model.Response model = response.body();
                                        if (model.isStatus()) {
                                            progressDialog.dismiss();
                                            list.remove(position);
                                            notifyItemChanged(position);
                                            dialog.dismiss();
                                            Toast.makeText(context, "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(context, "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<com.example.maxitaxidriver.Model.Response> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.CENTER);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookingNo, email, status, date, name, pickup, pickupTime, dropoff, totalPassager, price, totalDistance, totalTime, flightNo, discription, paymentType;
        Button btnConfirm, btnNotDone;
        LinearLayout linear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookingNo = itemView.findViewById(R.id.bookingNo);
            email = itemView.findViewById(R.id.email);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            pickup = itemView.findViewById(R.id.pickup);
            dropoff = itemView.findViewById(R.id.dropoff);
            totalPassager = itemView.findViewById(R.id.totalPassager);
            price = itemView.findViewById(R.id.price);
            totalDistance = itemView.findViewById(R.id.totalDistance);
            totalTime = itemView.findViewById(R.id.totalTime);
            flightNo = itemView.findViewById(R.id.flightNo);
            discription = itemView.findViewById(R.id.discription);
            status = itemView.findViewById(R.id.status);
            pickupTime = itemView.findViewById(R.id.pickupTime);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            btnNotDone = itemView.findViewById(R.id.btnNotDone);
            linear = itemView.findViewById(R.id.linear);

            apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
            progressDialog = new CustomProgressDialog(context);
        }
    }
}
