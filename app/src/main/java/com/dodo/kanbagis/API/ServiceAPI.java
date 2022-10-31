package com.dodo.kanbagis.API;

import com.dodo.kanbagis.API.response.advertApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceAPI {
    @GET("adverts")
    Call<List<advertApi>>getAllAdverts();
}
