package com.xeylyne.myplace.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultIDPlace {
    @SerializedName("ID_PLACE")
    @Expose
    private String iDPLACE;

    public String getIDPLACE() {
        return iDPLACE;
    }

    public void setIDPLACE(String iDPLACE) {
        this.iDPLACE = iDPLACE;
    }
}
