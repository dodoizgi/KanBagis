package com.dodo.kanbagis.API;

import com.dodo.kanbagis.API.response.Advert;
import com.dodo.kanbagis.API.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ServiceAPI {
    @GET("api/adverts")
    Call<List<Advert>>getAllAdverts();

    @POST("api/adverts")
    Call<Advert> postAdvert(@Body Advert advert);

    @PUT("api/adverts")
    Call<Advert> putAdvert(@Body Advert advert);

    @DELETE("api/adverts")
    Call<Advert> deleteAdvert(@Body Advert advert);

    @GET("user")
    Call<List<User>>getAllUsers();

    @GET("user/{id}")
    Call<List<User>>getUserById(@Query("Id") Integer id);

    @GET("user/login")
    Call<List<User>>getUserByMailAndPassword(@Query("mail") String mail, @Query("password") String password);

    @GET("user/adverts")
    Call<List<Advert>>getAdvertsByUserId(@Query("id") String id);

    @POST("user")
    Call<User> postUser(@Body User user);

    @PUT("user")
    Call<User> putUser(@Body User user);

    @DELETE("user")
    Call<User> deleteUser(@Body User user);

}
