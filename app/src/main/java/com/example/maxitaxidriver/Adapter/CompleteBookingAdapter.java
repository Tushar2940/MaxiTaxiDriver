package com.example.maxitaxidriver.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxitaxidriver.R;
import com.example.maxitaxidriver.Model.ResponseModel;

import java.util.List;

public class CompleteBookingAdapter extends RecyclerView.Adapter<CompleteBookingAdapter.ViewHolder> {

    Context context;
    List<ResponseModel.Responsebody> list;

    public CompleteBookingAdapter(Context context, List<ResponseModel.Responsebody> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CompleteBookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_data_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompleteBookingAdapter.ViewHolder holder, int position) {
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
        holder.status.setText(""+list.get(position).getStatus());
        holder.pickupTime.setText("" + list.get(position).getTime());

        if (list.get(position).getStatus().equals("1")) {
            holder.status.setText("Pending");
        } else if (list.get(position).getStatus().equals("2")){
            holder.status.setText("Pending");
        }else if (list.get(position).getStatus().equals("3")){
            holder.status.setText("Not Confirm");
        }else if (list.get(position).getStatus().equals("6")){
            holder.status.setText("Assign Back");
        }
        else
        {
            holder.status.setText("Complete");
        }
        holder.linear.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookingNo,email,status,date,name,pickup,pickupTime,dropoff,totalPassager,price,totalDistance,totalTime,flightNo,discription,paymentType;
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
            linear = itemView.findViewById(R.id.linear);
        }
    }
}
