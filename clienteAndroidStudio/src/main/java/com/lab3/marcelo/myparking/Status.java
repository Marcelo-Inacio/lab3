package com.lab3.marcelo.myparking;

/**
 * Created by Marcelo on 11/12/2015.
 */
public class Status {

    private String status;
    private String obs;

    public Status(){}

    public Status(String status, String obs){
        this.status = status;
        this.obs = obs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
