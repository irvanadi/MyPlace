package com.xeylyne.myplace.APIPackage;

import com.xeylyne.myplace.Models.RequestIDPlace;
import com.xeylyne.myplace.Models.RequestPlace;
import com.xeylyne.myplace.Models.RequestUser;
import com.xeylyne.myplace.Models.ResultMessage;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIPackage {

    @FormUrlEncoded
    @POST("pl_login.php")
    Call<RequestUser> login(@Field("EMAIL") String EMAIL,
                            @Field("PASS") String PASS);

    @GET("pl_getPlace.php")
    Call<RequestPlace> getPlace();

    @FormUrlEncoded
    @POST("pl_insertPlace.php")
    Call<ResultMessage> insertPLace(@Field("NAME_PLACE") String NAME_PLACE,
                                    @Field("ADDRESS_PLACE") String ADDRESS_PLACE,
                                    @Field("FAV_FOOD") String FAV_FOOD,
                                    @Field("AVG_PRICE") String AVG_PRICE,
                                    @Field("REVIEW") String REVIEW,
                                    @Field("ID_USER") int ID_USER);

    @Multipart
    @POST("pl_insertPlace.php")
    Call<ResultMessage> insertPLace2(@Part("NAME_PLACE") RequestBody NAME_PLACE,
                                     @Part("ADDRESS_PLACE") RequestBody ADDRESS_PLACE,
                                     @Part("FAV_FOOD") RequestBody FAV_FOOD,
                                     @Part("AVG_PRICE") RequestBody AVG_PRICE,
                                     @Part("REVIEW") RequestBody REVIEW,
                                     @Part("ID_USER") RequestBody ID_USER,
                                     @Part MultipartBody.Part IMAGE);

    @FormUrlEncoded
    @POST("pl_register.php")
    Call<ResultMessage> register(@Field("NICKNAME") String NICKNAME,
                                 @Field("PASS") String PASS,
                                 @Field("EMAIL") String EMAIL,
                                 @Field("CONTACT") String CONTACT,
                                 @Field("INSTAGRAM") String INSTAGRAM);

    @FormUrlEncoded
    @POST("pl_deletePlace.php")
    Call<ResultMessage> delete(@Field("ID_PLACE") int ID_PLACE);

    @FormUrlEncoded
    @POST("pl_IDPlace.php")
    Call<RequestIDPlace> id_places(@Field("NAME_PLACE") String NAME_PLACE,
                                   @Field("ID_USER") int ID_USER);

}
