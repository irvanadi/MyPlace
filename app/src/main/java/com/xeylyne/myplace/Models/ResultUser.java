package com.xeylyne.myplace.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultUser  {

    @SerializedName("ID_USER")
    @Expose
    private String iDUSER;
    @SerializedName("NICKNAME")
    @Expose
    private String nICKNAME;
    @SerializedName("CONTACT")
    @Expose
    private String cONTACT;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("INSTAGRAM")
    @Expose
    private String iNSTAGRAM;

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

    public String getCONTACT() {
        return cONTACT;
    }

    public void setCONTACT(String cONTACT) {
        this.cONTACT = cONTACT;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getINSTAGRAM() {
        return iNSTAGRAM;
    }

    public void setINSTAGRAM(String iNSTAGRAM) {
        this.iNSTAGRAM = iNSTAGRAM;
    }
}
