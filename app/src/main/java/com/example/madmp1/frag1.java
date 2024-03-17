package com.example.madmp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class frag1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    DBManager dbManager;
    ImageView caracc,gymacc,clgacc,sprtacc;
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
        sprtacc = view.findViewById(R.id.sports_acc);
        dbManager= new DBManager(getContext());

        String email = dbManager.get_email();
        if(email!=null) {
            usersname.setText(email);
        }

        ImageSlider imageSlider = view.findViewById(R.id.imageslider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.offer1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.offer2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.offer3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.offer4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.offer5, ScaleTypes.FIT));

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



        // Other setup tasks can go here

        return view;
    }

    // return inflater.inflate(R.layout.fragment_frag1, container, false);

}