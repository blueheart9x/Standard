package com.elcom.model.dto;

public class ResponseSuccess {

    private String returnMes;

    public ResponseSuccess() {
        this.returnMes = "Success"; //Init val
    }

    public String getReturnMes() {
        return returnMes;
    }

    public void setReturnMes(String returnMes) {
        this.returnMes = returnMes;
    }
}
