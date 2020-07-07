package com.bahrijar.invetarisapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;


public class SharedPrefManager {

    public static final String SP_LOGIN_APP = "spLoginApp";

    public static final String SP_NAMA = "spNama";
    public static final String SP_TOKEN = "spToken";
    public static final String SP_NOINDUK= "spNoInduk";
    public static final String SP_ROLE= "spRole";
    public static final String SP_KELAS= "spKelas";
    public static final String SP_ID_KELAS= "spIdKelas";

    // Laporan
    public static final String SP_LAPORAN= "spLaporan";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;


    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_LOGIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }



    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }


    public String getSPKelas(){
        return sp.getString(SP_KELAS, "");
    }


    public String getSpIdKelas(){
        return sp.getString(SP_ID_KELAS, "");
    }
    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSpNoinduk(){
        return sp.getString(SP_NOINDUK, "");
    }

    public String getSPRole(){
        return sp.getString(SP_ROLE, "");
    }

    public String getSpLaporan(){
        return sp.getString(SP_LAPORAN, "");
    }

    public String getSPToken(){
        return sp.getString(SP_TOKEN, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
