package com.example.maxitaxidriver;

import java.util.List;

public class LoginResponse {

    Responsebody ResultData;
    boolean ResultStatus;
    String ResultMessage;

    public LoginResponse() {
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
        int DriverID;

        public int getDriverID() {
            return DriverID;
        }

        public void setDriverID(int driverID) {
            DriverID = driverID;
        }
    }
}
