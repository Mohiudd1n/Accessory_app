package com.example.madmp1;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class frag4 extends Fragment {
    Button logout;
    Fragment currentFragment;
    CardView cardView,cardView2;

    TextView names,emails,showorders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_frag4, container, false);
        DBManager dbManager = new DBManager(getContext());

        cardView = v.findViewById(R.id.cardview);
        cardView2 = v.findViewById(R.id.cardview2);
        emails = v.findViewById(R.id.emails);
        names = v.findViewById(R.id.names);

        String email = dbManager.get_email();
        String name = dbManager.get_name(email);

        name = StringUtils.capitalize(name);

        names.setText(name);
        emails.setText(email);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), your_orders.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
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