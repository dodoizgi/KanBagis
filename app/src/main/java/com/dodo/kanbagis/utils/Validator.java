package com.dodo.kanbagis.utils;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Validator {

    private static Validator instance;
    private ArrayList<Boolean> valids = new ArrayList<Boolean>();

    protected Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) instance = new Validator();
        instance.valids.clear();
        return instance;
    }

    public Validator maxLength(TextInputLayout inputLayout, int length, String msg) {
        String str = inputLayout.getEditText().getText().toString();

        if (str.length() > length) {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }

    public Validator length(TextInputLayout inputLayout, int length, String msg) {
        String str = inputLayout.getEditText().getText().toString();

        if (str.length() == length) {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }
    public Validator minLength(TextInputLayout inputLayout, int length, String msg, boolean checkEquality) {

        if (checkEquality) {
            String str = inputLayout.getEditText().getText().toString();

            if (str.length() <= length) {
                inputLayout.setErrorEnabled(true);
                inputLayout.setError(msg);
                valids.add(false);
            } else {
                inputLayout.setErrorEnabled(false);
                inputLayout.setError(null);
                valids.add(true);
            }
        } else
            return minLength(inputLayout, length, msg);


        return this;
    }

    public Validator minLength(TextInputLayout inputLayout, int length, String msg) {
        String str = inputLayout.getEditText().getText().toString();

        if (str.length() < length) {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }

    public Validator isNotEmpty(EditText inputLayout, String msg) {
        String str = inputLayout.getText().toString();

        if (StringUtils.isNullOrEmpty(str)) {
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }

    public Validator isBlood(EditText inputLayout, String msg) {
        String blood = inputLayout.getText().toString();

        if (!StringUtils.isBlood(blood)) {
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }

    public Validator isRh(EditText inputLayout, String msg) {
        String rh = inputLayout.getText().toString();

        if (!StringUtils.isRh(rh)) {
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }

    public Validator isPhoneNumberCharacter(EditText inputLayout, String msg) {
        String phone = inputLayout.getText().toString();

            if (phone.length() != 10) {
                inputLayout.setError(msg);
                valids.add(false);
            } else {
                inputLayout.setError(null);
                valids.add(true);
            }

        return this;
    }

    public Validator isEmail(TextInputLayout inputLayout, String msg) {
        String str = inputLayout.getEditText().getText().toString();

        if (StringUtils.isEmail(str)) {
            inputLayout.setErrorEnabled(true);
            inputLayout.setError(msg);
            valids.add(false);
        } else {
            inputLayout.setErrorEnabled(false);
            inputLayout.setError(null);
            valids.add(true);
        }

        return this;
    }

    public Validator isSame(TextInputLayout first, TextInputLayout second, String msg) {
        String str = first.getEditText().getText().toString();
        String str2 = second.getEditText().getText().toString();

        if (StringUtils.isNullOrEmpty(str) || !str.equals(str2)) {
            second.setErrorEnabled(true);
            second.setError(msg);
            valids.add(false);
        } else {
            second.setErrorEnabled(false);
            second.setError(null);
            valids.add(true);
        }

        return this;
    }

    public boolean isValid() {
        return !instance.valids.contains(false);
    }
}
