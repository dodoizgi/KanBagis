package com.dodo.kanbagis.API;

import com.dodo.kanbagis.API.response.Advert;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceAPI {
    @GET("adverts")
    Call<List<Advert>>getAllAdverts();

    @POST("adverts")
    Call<Advert> postAdvert(@Body Advert advert);
}
