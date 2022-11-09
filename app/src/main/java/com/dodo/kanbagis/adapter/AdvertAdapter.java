package com.dodo.kanbagis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.dodo.kanbagis.API.response.Advert;

import com.dodo.kanbagis.activity.MainActivity;
import com.dodo.kanbagis.databinding.AdvertItemBinding;
import com.dodo.kanbagis.fragment.AdvertDialogFragment;
import com.dodo.kanbagis.fragment.MyDonationBloodsFragment;

import java.util.ArrayList;
import java.util.List;

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.ViewHolder> {

    private final List<Advert> list = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        AdvertItemBinding binding = AdvertItemBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Advert advert = list.get(position);

        String bloodGroup = advert.getBloodGroup() + " " + advert.getRh();
        holder.binding.blondeGroup.setText(bloodGroup);

        holder.binding.blondeMessage.setText(advert.getMessages());
        holder.binding.blondeAdressDetail.setText(advert.getAdress());
        holder.binding.blondePhone.setText(advert.getPhone());
        holder.binding.advertItem.setOnLongClickListener(v -> {
            FragmentActivity activity = (FragmentActivity)(context);
            FragmentManager fm = activity.getSupportFragmentManager();
            AdvertDialogFragment advertDialogFragment = new AdvertDialogFragment();
            advertDialogFragment.show(fm,"fragment alert");
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public AdvertItemBinding binding;

        public ViewHolder(AdvertItemBinding bindTo) {
            super(bindTo.getRoot());
            binding = bindTo;
        }
    }

    public void changeAll(List<Advert> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
}
