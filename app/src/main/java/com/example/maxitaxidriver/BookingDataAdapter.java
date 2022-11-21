package com.example.maxitaxidriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookingDataAdapter extends RecyclerView.Adapter<BookingDataAdapter.ViewHolder> {

    Context context;
    List<ResponseModel.Responsebody> list;

    public BookingDataAdapter(Context context, List<ResponseModel.Responsebody> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BookingDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_data_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingDataAdapter.ViewHolder holder, int position) {
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
//        if (list.get(position).getPaymentType().equals("1"))
//        {
//            holder.paymentType.setText("Cash");
//        }else {
//            holder.paymentType.setText("Card");
//        }
        holder.pickupTime.setText("" + list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookingNo,email,status,date,name,pickup,pickupTime,dropoff,totalPassager,price,totalDistance,totalTime,flightNo,discription,paymentType;
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
        }
    }
}
