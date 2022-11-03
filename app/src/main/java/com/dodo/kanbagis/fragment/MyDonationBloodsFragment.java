package com.dodo.kanbagis.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dodo.kanbagis.API.ApiClient;
import com.dodo.kanbagis.API.ServiceAPI;
import com.dodo.kanbagis.API.response.Advert;
import com.dodo.kanbagis.adapter.AdvertAdapter;
import com.dodo.kanbagis.databinding.FragmentMyDonationBloodsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDonationBloodsFragment extends Fragment {

    private FragmentMyDonationBloodsBinding binding;
    AdvertAdapter advertAdapter = new AdvertAdapter();
    private List<Advert> advertList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyDonationBloodsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.blondeList.setAdapter(advertAdapter);
        getMyAdverts();
    }

    private void getMyAdverts() {
        ServiceAPI serviceAPI = ApiClient.getRetrofit().create(ServiceAPI.class);
        Call<List<Advert>> call = serviceAPI.getAdvertsByUserId("3");
        call.enqueue(new Callback<List<Advert>>() {
            @Override
            public void onResponse(Call<List<Advert>> call, Response<List<Advert>> response) {
                if (!response.isSuccessful())
                    return;

                advertList.clear();
                advertList = response.body();
                advertAdapter.changeAll(advertList);
            }

            @Override
            public void onFailure(Call<List<Advert>> call, Throwable t) {
                System.out.println("fail : " + t);
            }
        });
    }
}