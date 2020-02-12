package com.xeylyne.myplace.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RequestPlace {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private ArrayList<ResultPlace> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ArrayList<ResultPlace> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultPlace> result) {
        this.result = result;
    }
}
