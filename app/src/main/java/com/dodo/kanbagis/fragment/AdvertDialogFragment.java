package com.dodo.kanbagis.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.dodo.kanbagis.R;

import java.util.Objects;

public class AdvertDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_advert_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.approve, (dialog, id) -> {
                    // sign in the user ...
                })
                .setNegativeButton(R.string.cancel, (dialog, id) -> Objects.requireNonNull(AdvertDialogFragment.this.getDialog()).cancel());

        return builder.create();
    }

}
