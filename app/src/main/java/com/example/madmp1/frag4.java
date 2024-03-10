package com.example.madmp1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class frag4 extends Fragment {
    Button logout,showorders;
    Fragment currentFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_frag4, container, false);
        DBManager dbManager = new DBManager(getContext());

        showorders = v.findViewById(R.id.my_orders_button);
        logout = v.findViewById(R.id.logout);

        showorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),your_orders.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.logout();

                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return v;
    }
}