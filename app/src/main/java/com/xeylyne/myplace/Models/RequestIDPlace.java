package com.xeylyne.myplace.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RequestIDPlace {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private ArrayList<ResultIDPlace> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArrayList<ResultIDPlace> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultIDPlace> result) {
        this.result = result;
    }
}
