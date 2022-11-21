package com.example.maxitaxidriver;

import java.util.List;

public class ResponseModel {

    List<Responsebody> ResultData = null;
    boolean ResultStatus;
    String ResultMessage;

    public ResponseModel() {
    }

    public List<Responsebody> getResultData() {
        return ResultData;
    }

    public void setResultData(List<Responsebody> resultData) {
        ResultData = resultData;
    }

    public boolean isResultStatus() {
        return ResultStatus;
    }

    public void setResultStatus(boolean resultStatus) {
        ResultStatus = resultStatus;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String resultMessage) {
        ResultMessage = resultMessage;
    }

    public static class Responsebody {
        int id;
        String Pick_Up_Address;
        String Name;
        String Email;
        String Drop_Off_Address;
        String ContactNo;
        String Time;
        String Hours;
        String Minutes;
        String airportType;
        String Date;
        String Passengers;
        String Price;
        String PaymentType;
        String totalTime;
        String totalDistance;
        String Remarks;
        String Flightno;
        String Status;
        String No_off_passanger;
        String time_to_destination;

        public Responsebody() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPick_Up_Address() {
            return Pick_Up_Address;
        }

        public void setPick_Up_Address(String pick_Up_Address) {
            Pick_Up_Address = pick_Up_Address;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getDrop_Off_Address() {
            return Drop_Off_Address;
        }

        public void setDrop_Off_Address(String drop_Off_Address) {
            Drop_Off_Address = drop_Off_Address;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public void setContactNo(String contactNo) {
            ContactNo = contactNo;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        public String getHours() {
            return Hours;
        }

        public void setHours(String hours) {
            Hours = hours;
        }

        public String getMinutes() {
            return Minutes;
        }

        public void setMinutes(String minutes) {
            Minutes = minutes;
        }

        public String getAirportType() {
            return airportType;
        }

        public void setAirportType(String airportType) {
            this.airportType = airportType;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            Date = date;
        }

        public String getPassengers() {
            return Passengers;
        }

        public void setPassengers(String passengers) {
            Passengers = passengers;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public String getTotalTime() {
            return totalTime;
        }

        public void setTotalTime(String totalTime) {
            this.totalTime = totalTime;
        }

        public String getTotalDistance() {
            return totalDistance;
        }

        public void setTotalDistance(String totalDistance) {
            this.totalDistance = totalDistance;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            Remarks = remarks;
        }

        public String getFlightno() {
            return Flightno;
        }

        public void setFlightno(String flightno) {
            Flightno = flightno;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getNo_off_passanger() {
            return No_off_passanger;
        }

        public void setNo_off_passanger(String no_off_passanger) {
            No_off_passanger = no_off_passanger;
        }

        public String getTime_to_destination() {
            return time_to_destination;
        }

        public void setTime_to_destination(String time_to_destination) {
            this.time_to_destination = time_to_destination;
        }
    }
}
