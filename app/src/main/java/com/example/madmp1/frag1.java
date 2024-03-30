package com.example.madmp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.tabs.TabLayout;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class frag1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    DBManager dbManager;

    ImageView caracc,gymacc,clgacc,sprtacc,img1;
    TextView usersname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);

        // Initialize your objects here
        caracc = view.findViewById(R.id.caracc);
        gymacc = view.findViewById(R.id.gymacc);
        clgacc = view.findViewById(R.id.college_acc);
        usersname = view.findViewById(R.id.username1);
        img1 = view.findViewById(R.id.img1);
        sprtacc = view.findViewById(R.id.sports_acc);
        dbManager= new DBManager(getContext());

        String email = dbManager.get_email();
        if(email!=null) {
            String name = dbManager.get_name(email);
            name = StringUtils.capitalize(name);
            usersname.setText(name);
        }

        ImageSlider imageSlider = view.findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.off1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.off2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.off3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.off4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.off6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        // Set a click listener on the button
        caracc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                startActivity(new Intent(v.getContext(),car_accessories.class));
            }
        });

        gymacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                startActivity(new Intent(v.getContext(),gym_accessories.class));
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new frag4();
                getFragmentManager().beginTransaction().replace(R.id.framelayout,frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });

        clgacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                startActivity(new Intent(v.getContext(),college_accessory.class));
            }
        });

        sprtacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                startActivity(new Intent(v.getContext(), sports_accessory.class));
            }
        });


        return view;
    }

    // return inflater.inflate(R.layout.fragment_frag1, container, false);

}