package com.dodo.kanbagis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dodo.kanbagis.API.response.advertApi;

import com.dodo.kanbagis.databinding.BlondeItemBinding;
import com.dodo.kanbagis.module.Blood;

import java.util.ArrayList;
import java.util.List;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.ViewHolder> {

    private final List<advertApi> list = new ArrayList<>();

    public BloodAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        BlondeItemBinding binding = BlondeItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        advertApi blood = list.get(position);

        String bloodGroup = blood.getBloodGroup() + " Rh" + blood.getRh();
        holder.binding.blondeGroup.setText(bloodGroup);

        holder.binding.blondeMessage.setText(blood.getMessages());
        holder.binding.blondeAdressDetail.setText(blood.getAdress());
        holder.binding.blondePhone.setText(blood.getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public BlondeItemBinding binding;

        public ViewHolder(BlondeItemBinding bindTo) {
            super(bindTo.getRoot());
            binding = bindTo;
        }
    }

    public void changeAll(List<advertApi> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}
