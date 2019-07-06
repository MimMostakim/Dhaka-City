package com.example.mim.dhakacity;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginPreference {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private final String LOGGED_IN_STATUS = "logged_in_status";

    public LoginPreference(Context context){
        preferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setLoginStatus(boolean status){
        editor.putBoolean(LOGGED_IN_STATUS, status);
        editor.commit();
    }

    public boolean getLoginStatus(){
        return preferences.getBoolean(LOGGED_IN_STATUS, false);
    }
}
