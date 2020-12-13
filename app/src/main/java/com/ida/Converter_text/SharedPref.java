package com.ida.Converter_text;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private static SharedPref instance = null;
   public SharedPreferences mySharedPref;
    public SharedPref(Context context){
        mySharedPref = context.getSharedPreferences("fileName", Context.MODE_PRIVATE);
    }

         //Bu method tungi rejimni saqlaydi, True yoki False
    public void setNightModeState (Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.apply();
    }
    //Bu method Tungi rejimni load qiladi
    public Boolean loadNightMode(){
        Boolean state =mySharedPref.getBoolean("NightMode", false);
        return state;
    }
}
