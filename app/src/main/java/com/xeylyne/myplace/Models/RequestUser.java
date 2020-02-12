package com.xeylyne.myplace.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RequestUser {

    @SerializedName("result")
    @Expose
    private ArrayList<ResultUser> result = null;

    public ArrayList<ResultUser> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultUser> result) {
        this.result = result;
    }
}
