package com.bahrijar.invetarisapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bahrijar.invetarisapp.R;
import com.bahrijar.invetarisapp.activity.LoginActivity;
import com.bahrijar.invetarisapp.utils.SharedPrefManager;

public class AccountFragment extends Fragment {
    SharedPrefManager sharedPrefManager;
   TextView tvLogout, tvName, tvNim;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);


        sharedPrefManager = new SharedPrefManager(view.getContext());

        tvLogout = view.findViewById(R.id.tv_logout);
//        tvName = view.findViewById(R.id.tv_name);
//        tvNim = view.findViewById(R.id.tv_nim);
//
//        tvName.setText(sharedPrefManager.getSPNama());
//        tvNim.setText(sharedPrefManager.getSpNoinduk());

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getActivity(), LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
            }
        });
        return view;
    }
}
