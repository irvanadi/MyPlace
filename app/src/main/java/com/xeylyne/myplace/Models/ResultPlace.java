package com.xeylyne.myplace.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultPlace {

    @SerializedName("ID_PLACE")
    @Expose
    private String iDPLACE;
    @SerializedName("NAME_PLACE")
    @Expose
    private String nAMEPLACE;
    @SerializedName("ADDRESS_PLACE")
    @Expose
    private String aDDRESSPLACE;
    @SerializedName("FAV_FOOD")
    @Expose
    private String fAVFOOD;
    @SerializedName("AVG_PRICE")
    @Expose
    private String aVGPRICE;
    @SerializedName("REVIEW")
    @Expose
    private String rEVIEW;
    @SerializedName("ID_USER")
    @Expose
    private String iDUSER;
    @SerializedName("NICKNAME")
    @Expose
    private String nICKNAME;

    public String getIDPLACE() {
        return iDPLACE;
    }

    public void setIDPLACE(String iDPLACE) {
        this.iDPLACE = iDPLACE;
    }

    public String getNAMEPLACE() {
        return nAMEPLACE;
    }

    public void setNAMEPLACE(String nAMEPLACE) {
        this.nAMEPLACE = nAMEPLACE;
    }

    public String getADDRESSPLACE() {
        return aDDRESSPLACE;
    }

    public void setADDRESSPLACE(String aDDRESSPLACE) {
        this.aDDRESSPLACE = aDDRESSPLACE;
    }

    public String getFAVFOOD() {
        return fAVFOOD;
    }

    public void setFAVFOOD(String fAVFOOD) {
        this.fAVFOOD = fAVFOOD;
    }

    public String getAVGPRICE() {
        return aVGPRICE;
    }

    public void setAVGPRICE(String aVGPRICE) {
        this.aVGPRICE = aVGPRICE;
    }

    public String getREVIEW() {
        return rEVIEW;
    }

    public void setREVIEW(String rEVIEW) {
        this.rEVIEW = rEVIEW;
    }

    public String getIDUSER() {
        return iDUSER;
    }

    public void setIDUSER(String iDUSER) {
        this.iDUSER = iDUSER;
    }

    public String getNICKNAME() {
        return nICKNAME;
    }

    public void setNICKNAME(String nICKNAME) {
        this.nICKNAME = nICKNAME;
    }
}
