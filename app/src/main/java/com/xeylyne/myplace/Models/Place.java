package com.xeylyne.myplace.Models;

public class Place {

    int ID_PLACE;
    String NAME_PLACE, ADDRESS_PLACE, FAV_FOOD, AVG_PRICE, REVIEW;
    byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getID_PLACE() {
        return ID_PLACE;
    }

    public void setID_PLACE(int ID_PLACE) {
        this.ID_PLACE = ID_PLACE;
    }

    public String getNAME_PLACE() {
        return NAME_PLACE;
    }

    public void setNAME_PLACE(String NAME_PLACE) {
        this.NAME_PLACE = NAME_PLACE;
    }

    public String getADDRESS_PLACE() {
        return ADDRESS_PLACE;
    }

    public void setADDRESS_PLACE(String ADDRESS_PLACE) {
        this.ADDRESS_PLACE = ADDRESS_PLACE;
    }

    public String getFAV_FOOD() {
        return FAV_FOOD;
    }

    public void setFAV_FOOD(String FAV_FOOD) {
        this.FAV_FOOD = FAV_FOOD;
    }

    public String getAVG_PRICE() {
        return AVG_PRICE;
    }

    public void setAVG_PRICE(String AVG_PRICE) {
        this.AVG_PRICE = AVG_PRICE;
    }

    public String getREVIEW() {
        return REVIEW;
    }

    public void setREVIEW(String REVIEW) {
        this.REVIEW = REVIEW;
    }
}
