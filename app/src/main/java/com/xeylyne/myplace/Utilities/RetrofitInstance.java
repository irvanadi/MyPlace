package com.xeylyne.myplace.Utilities;

import com.xeylyne.myplace.APIPackage.APIPackage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static String URL = "https://irvanlyne.000webhostapp.com/";

    public static Retrofit setInstance(){
        return new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static APIPackage getInstance(){
        return setInstance().create(APIPackage.class);
    }
}
